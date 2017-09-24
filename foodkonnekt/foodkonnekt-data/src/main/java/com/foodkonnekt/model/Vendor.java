package com.foodkonnekt.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vendor")
public class Vendor {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "companyId")
    private String companyId;
    
    @Column(name = "vendor_uid")
    private String vendorUid;

    public String getVendorUid() {
		return vendorUid;
	}

	public void setVendorUid(String vendorUid) {
		this.vendorUid = vendorUid;
	}

	public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roleid", referencedColumnName = "id")
    private Role role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pos_id", referencedColumnName = "pos_id")
    private Pos pos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * @return the pos
     */
    public Pos getPos() {
        return pos;
    }

    /**
     * @param pos
     *            the pos to set
     */
    public void setPos(Pos pos) {
        this.pos = pos;
    }

}
