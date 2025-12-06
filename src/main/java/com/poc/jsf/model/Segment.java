package com.poc.jsf.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Segment implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String name;
    private List<Field> fields;
    private boolean expanded;

    public Segment() {
        this.fields = new ArrayList<>();
        this.expanded = true;
    }

    public Segment(String id, String name) {
        this();
        this.id = id;
        this.name = name;
    }

    public void addField(Field field) {
        if (!this.fields.contains(field)) {
            this.fields.add(field);
        }
    }

    public void removeField(Field field) {
        this.fields.removeIf(f -> f.getName().equals(field.getName()));
    }

    public void removeFieldByName(String fieldName) {
        this.fields.removeIf(f -> f.getName().equals(fieldName));
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() { 
        return name; 
    }
    
    public void setName(String name) { 
        this.name = name; 
    }
    
    public List<Field> getFields() { 
        return fields; 
    }
    
    public void setFields(List<Field> fields) { 
        this.fields = fields; 
    }
    
    public boolean isExpanded() { 
        return expanded; 
    }
    
    public void setExpanded(boolean expanded) { 
        this.expanded = expanded; 
    }
}

