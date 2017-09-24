package com.foodkonnekt.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.envers.Audited;


@Entity
@Table(name="tender")
@XmlRootElement(name="Tender")
@Audited
public class Tender implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	
	
	
	@Basic
	@Column(length=100)
	private String instructions;
	
	
	
	@Basic
	@Column(length=100)
	private String visible;
	
	
	
	
	@Basic
	@Column(length=100)
	private String editable;
	
	
	
	@Basic
	@Column(length=100)
	private String label;
	
	
	
	@Basic
	@Column(length=100)
	private String labelKey;
	
	
	
	@Basic
	@Column(length=100)
	private String opensCashDrawer;
	
	

	@Basic
	private Boolean isEnabled;



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getInstructions() {
		return instructions;
	}



	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}



	public String getVisible() {
		return visible;
	}



	public void setVisible(String visible) {
		this.visible = visible;
	}



	public String getEditable() {
		return editable;
	}



	public void setEditable(String editable) {
		this.editable = editable;
	}



	public String getLabel() {
		return label;
	}



	public void setLabel(String label) {
		this.label = label;
	}



	public String getLabelKey() {
		return labelKey;
	}



	public void setLabelKey(String labelKey) {
		this.labelKey = labelKey;
	}



	public String getOpensCashDrawer() {
		return opensCashDrawer;
	}



	public void setOpensCashDrawer(String opensCashDrawer) {
		this.opensCashDrawer = opensCashDrawer;
	}



	public Boolean getIsEnabled() {
		return isEnabled;
	}



	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((editable == null) ? 0 : editable.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((instructions == null) ? 0 : instructions.hashCode());
		result = prime * result
				+ ((isEnabled == null) ? 0 : isEnabled.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result
				+ ((labelKey == null) ? 0 : labelKey.hashCode());
		result = prime * result
				+ ((opensCashDrawer == null) ? 0 : opensCashDrawer.hashCode());
		result = prime * result + ((visible == null) ? 0 : visible.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Tender)) {
			return false;
		}
		Tender other = (Tender) obj;
		if (editable == null) {
			if (other.editable != null) {
				return false;
			}
		} else if (!editable.equals(other.editable)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (instructions == null) {
			if (other.instructions != null) {
				return false;
			}
		} else if (!instructions.equals(other.instructions)) {
			return false;
		}
		if (isEnabled == null) {
			if (other.isEnabled != null) {
				return false;
			}
		} else if (!isEnabled.equals(other.isEnabled)) {
			return false;
		}
		if (label == null) {
			if (other.label != null) {
				return false;
			}
		} else if (!label.equals(other.label)) {
			return false;
		}
		if (labelKey == null) {
			if (other.labelKey != null) {
				return false;
			}
		} else if (!labelKey.equals(other.labelKey)) {
			return false;
		}
		if (opensCashDrawer == null) {
			if (other.opensCashDrawer != null) {
				return false;
			}
		} else if (!opensCashDrawer.equals(other.opensCashDrawer)) {
			return false;
		}
		if (visible == null) {
			if (other.visible != null) {
				return false;
			}
		} else if (!visible.equals(other.visible)) {
			return false;
		}
		return true;
	}



	@Override
	public String toString() {
		return "Tender [id=" + id + ", instructions=" + instructions
				+ ", visible=" + visible + ", editable=" + editable
				+ ", label=" + label + ", labelKey=" + labelKey
				+ ", opensCashDrawer=" + opensCashDrawer + ", isEnabled="
				+ isEnabled + "]";
	}
	
	
	
	
	
}
