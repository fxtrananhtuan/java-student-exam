/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beanInfo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MesutOezil
 */
@Entity
@Table(name = "tbRole", catalog = "RoyalEducationalAcademy", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbRole.findAll", query = "SELECT t FROM TbRole t"),
    @NamedQuery(name = "TbRole.findByRoleID", query = "SELECT t FROM TbRole t WHERE t.roleID = :roleID"),
    @NamedQuery(name = "TbRole.findByRoleName", query = "SELECT t FROM TbRole t WHERE t.roleName = :roleName")})
public class TbRole implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "RoleID", nullable = false)
    private Integer roleID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "RoleName", nullable = false, length = 50)
    private String roleName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleID")
    private List<TbAccount> tbAccountList;

    public TbRole() {
    }

    public TbRole(Integer roleID) {
        this.roleID = roleID;
    }

    public TbRole(Integer roleID, String roleName) {
        this.roleID = roleID;
        this.roleName = roleName;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @XmlTransient
    public List<TbAccount> getTbAccountList() {
        return tbAccountList;
    }

    public void setTbAccountList(List<TbAccount> tbAccountList) {
        this.tbAccountList = tbAccountList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleID != null ? roleID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbRole)) {
            return false;
        }
        TbRole other = (TbRole) object;
        if ((this.roleID == null && other.roleID != null) || (this.roleID != null && !this.roleID.equals(other.roleID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beanInfo.TbRole[ roleID=" + roleID + " ]";
    }
    
}
