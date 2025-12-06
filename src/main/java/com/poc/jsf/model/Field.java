package com.poc.jsf.model;

import java.io.Serializable;

public class Field implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private String displayName;
    private boolean selected;

    public Field() {}

    public Field(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
        this.selected = false;
    }

    // Getters and Setters
    public String getName() { 
        return name; 
    }
    
    public void setName(String name) { 
        this.name = name; 
    }
    
    public String getDisplayName() { 
        return displayName; 
    }
    
    public void setDisplayName(String displayName) { 
        this.displayName = displayName; 
    }
    
    public boolean isSelected() { 
        return selected; 
    }
    
    public void setSelected(boolean selected) { 
        this.selected = selected; 
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Field field = (Field) obj;
        return name != null ? name.equals(field.name) : field.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}

