package com.foodkonnekt.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "paymentmode")
@XmlRootElement(name = "PaymentMode")
@Audited
public class PaymentMode implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Basic
    @Column(length = 100)
    private String editable;

    @Basic
    @Column(length = 100)
    private String labelKey;

    @Basic
    @Column(length = 100)
    private String label;

    @Basic
    @Column(length = 100)
    private String openCashDrawer;

    @Basic
    private Boolean enabled;

    @Basic
    private Boolean visible;
    
    @Column(name = "allow_payment_mode")
    private Integer allowPaymentMode;

    public Integer getAllowPaymentMode() {
		return allowPaymentMode;
	}

	public void setAllowPaymentMode(Integer allowPaymentMode) {
		this.allowPaymentMode = allowPaymentMode;
	}

	@Column(name = "pos_payment_mode_id")
    private String posPaymentModeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
    private Merchant merchant;

    public String getPosPaymentModeId() {
        return posPaymentModeId;
    }

    public void setPosPaymentModeId(String posPaymentModeId) {
        this.posPaymentModeId = posPaymentModeId;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEditable() {
        return editable;
    }

    public void setEditable(String editable) {
        this.editable = editable;
    }

    public String getLabelKey() {
        return labelKey;
    }

    public void setLabelKey(String labelKey) {
        this.labelKey = labelKey;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getOpenCashDrawer() {
        return openCashDrawer;
    }

    public void setOpenCashDrawer(String openCashDrawer) {
        this.openCashDrawer = openCashDrawer;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((editable == null) ? 0 : editable.hashCode());
        result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((label == null) ? 0 : label.hashCode());
        result = prime * result + ((labelKey == null) ? 0 : labelKey.hashCode());
        result = prime * result + ((openCashDrawer == null) ? 0 : openCashDrawer.hashCode());
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
        if (!(obj instanceof PaymentMode)) {
            return false;
        }
        PaymentMode other = (PaymentMode) obj;
        if (editable == null) {
            if (other.editable != null) {
                return false;
            }
        } else if (!editable.equals(other.editable)) {
            return false;
        }
        if (enabled == null) {
            if (other.enabled != null) {
                return false;
            }
        } else if (!enabled.equals(other.enabled)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
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
        if (openCashDrawer == null) {
            if (other.openCashDrawer != null) {
                return false;
            }
        } else if (!openCashDrawer.equals(other.openCashDrawer)) {
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
        return "PaymentMode [id=" + id + ", editable=" + editable + ", labelKey=" + labelKey + ", label=" + label
                        + ", openCashDrawer=" + openCashDrawer + ", enabled=" + enabled + ", visible=" + visible + "]";
    }
}
