package org.imogene.epicam.domain.entity.backup;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.imogene.lib.common.entity.GeoField;
import org.imogene.lib.common.entity.ImogBeanImpl;
import org.imogene.lib.common.entity.ImogBeanBck;
import org.imogene.epicam.domain.entity.LocalizedText;

/**
 * ImogBean implementation for the entity HorsUsage Backup
 * @author MEDES-IMPS
 */
@Entity
@Table(name = "HorsUsage_Bck")
public class HorsUsageBck extends ImogBeanBck {

	private static final long serialVersionUID = 4854249638435956157L;

	/* Description group fields */

	private String lot;

	private String type;

	@Column(columnDefinition = "TEXT")
	private String motif;

	/**
	 * Constructor
	 */
	public HorsUsageBck() {
	}

	/* Getters and Setters for Description group fields */

	public String getLot() {
		return lot;
	}

	public void setLot(String value) {
		lot = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String value) {
		type = value;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String value) {
		motif = value;
	}

}
