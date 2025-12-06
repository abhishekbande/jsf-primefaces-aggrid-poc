package com.poc.jsf.bean;

import com.poc.jsf.model.Field;
import com.poc.jsf.model.LoanData;
import com.poc.jsf.model.Segment;
import com.google.gson.Gson;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named("segmentationBean")
@SessionScoped
public class SegmentationBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Field> availableFields;
    private List<Field> filteredFields;
    private List<Segment> segments;
    private List<LoanData> loanDataList;
    private String searchText;
    private String selectedDataset;
    private String cutOffDate;
    private boolean includeEmptySegments;
    private boolean autoRefresh;
    private String activeTab;
    private String selectedSegmentGroup;
    private Field selectedField;
    private Segment selectedSegment;

    @PostConstruct
    public void init() {
        initializeFields();
        initializeSegments();
        initializeLoanData();
        
        this.selectedDataset = "DATACOI3.csv";
        this.cutOffDate = "31 July 2024";
        this.includeEmptySegments = true;
        this.autoRefresh = true;
        this.activeTab = "fields";
        this.selectedSegmentGroup = "Segment Group 1";
    }

    private void initializeFields() {
        availableFields = new ArrayList<>();
        availableFields.add(new Field("asOfDate", "As of Date"));
        availableFields.add(new Field("delinquencyStatus", "Delinquency Status"));
        availableFields.add(new Field("escrowFlag", "Escrow Flag"));
        availableFields.add(new Field("guarantor", "Guarantor"));
        availableFields.add(new Field("loanAge", "Loan Age"));
        availableFields.add(new Field("occupancy", "Occupancy"));
        availableFields.add(new Field("originalTerm", "Original Term"));
        availableFields.add(new Field("programType", "Program Type"));
        availableFields.add(new Field("propertyType", "Property Type"));
        availableFields.add(new Field("servicingFee", "Servicing Fee"));
        availableFields.add(new Field("state", "State"));
        
        filteredFields = new ArrayList<>(availableFields);
    }

    private void initializeSegments() {
        segments = new ArrayList<>();
        
        // Segment 1
        Segment segment1 = new Segment("seg1", "Segment 1");
        segment1.addField(new Field("delinquencyStatus", "Delinquency Status"));
        segment1.addField(new Field("escrowFlag", "Escrow Flag"));
        segment1.addField(new Field("loanAge", "Loan Age"));
        segments.add(segment1);
        
        // Segment 2
        Segment segment2 = new Segment("seg2", "Segment 2");
        segment2.addField(new Field("propertyType", "Property Type"));
        segment2.addField(new Field("state", "State"));
        segments.add(segment2);
    }

    private void initializeLoanData() {
        loanDataList = new ArrayList<>();
        
        // Sample data matching the image - hierarchical structure
        loanDataList.add(new LoanData("3.00-4.50", null, null, 961, new BigDecimal("363098387.00"), 0, true));
        loanDataList.add(new LoanData(null, "30 Days+", null, 18, new BigDecimal("5959948.00"), 1, true));
        loanDataList.add(new LoanData(null, null, ">0.00", 18, new BigDecimal("5959948.00"), 2, false));
        loanDataList.add(new LoanData(null, "60 Days+", null, 12, new BigDecimal("4137723.00"), 1, true));
        loanDataList.add(new LoanData(null, null, ">0.00", 12, new BigDecimal("4137723.00"), 2, false));
        loanDataList.add(new LoanData(null, "90 Days+", null, 1, new BigDecimal("293172.00"), 1, true));
        loanDataList.add(new LoanData(null, null, ">0.00", 1, new BigDecimal("293172.00"), 2, false));
        loanDataList.add(new LoanData(null, "Current", null, 930, new BigDecimal("352707544.00"), 1, true));
        loanDataList.add(new LoanData(null, null, ">0.00", 930, new BigDecimal("352707544.00"), 2, false));
    }

    public void filterFields() {
        if (searchText == null || searchText.trim().isEmpty()) {
            filteredFields = new ArrayList<>(availableFields);
        } else {
            String search = searchText.toLowerCase();
            filteredFields = availableFields.stream()
                .filter(f -> f.getDisplayName().toLowerCase().contains(search))
                .collect(Collectors.toList());
        }
    }

    public void selectField(Field field) {
        this.selectedField = field;
        showMessage(FacesMessage.SEVERITY_INFO, "Field Selected", 
            "\"" + field.getDisplayName() + "\" selected. Click + in a segment to add it.");
    }

    public void addFieldToSegment(String segmentId) {
        if (selectedField == null) {
            showMessage(FacesMessage.SEVERITY_WARN, "Warning", "Please select a field first");
            return;
        }
        
        Segment segment = segments.stream()
            .filter(s -> s.getId().equals(segmentId))
            .findFirst()
            .orElse(null);
            
        if (segment != null) {
            if (!segment.getFields().stream().anyMatch(f -> f.getName().equals(selectedField.getName()))) {
                segment.addField(new Field(selectedField.getName(), selectedField.getDisplayName()));
                showMessage(FacesMessage.SEVERITY_INFO, "Field Added", 
                    selectedField.getDisplayName() + " added to " + segment.getName());
            } else {
                showMessage(FacesMessage.SEVERITY_WARN, "Already Exists", 
                    selectedField.getDisplayName() + " is already in " + segment.getName());
            }
        }
    }

    public void removeFieldFromSegment(Segment segment, Field field) {
        if (segment != null && field != null) {
            segment.getFields().removeIf(f -> f.getName().equals(field.getName()));
            showMessage(FacesMessage.SEVERITY_INFO, "Field Removed", 
                field.getDisplayName() + " removed from " + segment.getName());
        }
    }

    public void addNewSegment() {
        int nextNum = segments.size() + 1;
        String newId = "seg" + nextNum;
        segments.add(new Segment(newId, "Segment " + nextNum));
        showMessage(FacesMessage.SEVERITY_INFO, "Segment Added", "Segment " + nextNum + " created");
    }

    public void removeSegment(Segment segment) {
        segments.remove(segment);
        showMessage(FacesMessage.SEVERITY_INFO, "Segment Removed", segment.getName() + " removed");
    }

    public void clearAllSegments() {
        for (Segment segment : segments) {
            segment.getFields().clear();
        }
        showMessage(FacesMessage.SEVERITY_INFO, "Cleared", "All segments cleared");
    }

    public void duplicateSegment(Segment segment) {
        if (segment != null) {
            int nextNum = segments.size() + 1;
            Segment newSegment = new Segment("seg" + nextNum, "Segment " + nextNum);
            for (Field field : segment.getFields()) {
                newSegment.addField(new Field(field.getName(), field.getDisplayName()));
            }
            segments.add(newSegment);
            showMessage(FacesMessage.SEVERITY_INFO, "Duplicated", 
                segment.getName() + " duplicated as Segment " + nextNum);
        }
    }

    public void toggleSegment(Segment segment) {
        segment.setExpanded(!segment.isExpanded());
    }

    public void collapseAllSegments() {
        for (Segment segment : segments) {
            segment.setExpanded(false);
        }
        showMessage(FacesMessage.SEVERITY_INFO, "Collapsed", "All segments collapsed");
    }

    public void expandAllSegments() {
        for (Segment segment : segments) {
            segment.setExpanded(true);
        }
        showMessage(FacesMessage.SEVERITY_INFO, "Expanded", "All segments expanded");
    }

    public void downloadReport() {
        showMessage(FacesMessage.SEVERITY_INFO, "Download", "Report download initiated");
    }

    public void switchTab(String tab) {
        this.activeTab = tab;
    }

    private void showMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(severity, summary, detail));
    }

    public String getLoanDataJson() {
        return new Gson().toJson(loanDataList);
    }

    // Getters and Setters
    public List<Field> getAvailableFields() { return availableFields; }
    public void setAvailableFields(List<Field> availableFields) { this.availableFields = availableFields; }
    public List<Field> getFilteredFields() { return filteredFields; }
    public void setFilteredFields(List<Field> filteredFields) { this.filteredFields = filteredFields; }
    public List<Segment> getSegments() { return segments; }
    public void setSegments(List<Segment> segments) { this.segments = segments; }
    public List<LoanData> getLoanDataList() { return loanDataList; }
    public void setLoanDataList(List<LoanData> loanDataList) { this.loanDataList = loanDataList; }
    public String getSearchText() { return searchText; }
    public void setSearchText(String searchText) { this.searchText = searchText; }
    public String getSelectedDataset() { return selectedDataset; }
    public void setSelectedDataset(String selectedDataset) { this.selectedDataset = selectedDataset; }
    public String getCutOffDate() { return cutOffDate; }
    public void setCutOffDate(String cutOffDate) { this.cutOffDate = cutOffDate; }
    public boolean isIncludeEmptySegments() { return includeEmptySegments; }
    public void setIncludeEmptySegments(boolean includeEmptySegments) { this.includeEmptySegments = includeEmptySegments; }
    public boolean isAutoRefresh() { return autoRefresh; }
    public void setAutoRefresh(boolean autoRefresh) { this.autoRefresh = autoRefresh; }
    public String getActiveTab() { return activeTab; }
    public void setActiveTab(String activeTab) { this.activeTab = activeTab; }
    public String getSelectedSegmentGroup() { return selectedSegmentGroup; }
    public void setSelectedSegmentGroup(String selectedSegmentGroup) { this.selectedSegmentGroup = selectedSegmentGroup; }
    public Field getSelectedField() { return selectedField; }
    public void setSelectedField(Field selectedField) { this.selectedField = selectedField; }
    public Segment getSelectedSegment() { return selectedSegment; }
    public void setSelectedSegment(Segment selectedSegment) { this.selectedSegment = selectedSegment; }
}
