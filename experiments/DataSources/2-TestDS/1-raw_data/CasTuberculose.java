package org.imogene.epicam.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;
import org.imogene.lib.common.binary.file.BinaryFile;
import org.imogene.lib.common.constants.NotificationConstants;
import org.imogene.lib.common.entity.GeoField;
import org.imogene.lib.common.entity.ImogActorImpl;
import org.imogene.lib.common.entity.ImogEntityImpl;
import org.imogene.lib.common.entity.IsGeoreferenced;

/**
 * ImogBean implementation for the entity CasTuberculose
 * @author MEDES-IMPS
 */
@Entity
public class CasTuberculose extends ImogEntityImpl {

	public static interface Columns {
		public static final String IDENTIFIANT = "identifiant";

		public static final String NUMREGTB = "numregtb";

		public static final String PATIENT = "patient";

		public static final String DATEDEBUTTRAITEMENT = "datedebuttraitement";

		public static final String TYPEPATIENT = "typepatient";

		public static final int TYPEPATIENT_NOUVEAUCAS = 0;

		public static final int TYPEPATIENT_REPRISEAPRESABANDON = 1;

		public static final int TYPEPATIENT_ECHEC = 2;

		public static final int TYPEPATIENT_RECHUTE = 3;

		public static final int TYPEPATIENT_AUTRE = 4;

		public static final String TYPEPATIENTPRECISIONS = "typepatientprecisions";

		public static final String FORMEMALADIE = "formemaladie";

		public static final int FORMEMALADIE_TPMPLUS = 0;

		public static final int FORMEMALADIE_TPMMOINS = 1;

		public static final int FORMEMALADIE_EXTRA_PULMONAIRE = 2;

		public static final String EXTRAPULMONAIREPRECISIONS = "extrapulmonaireprecisions";

		public static final String COTRIMOXAZOLE = "cotrimoxazole";

		public static final int COTRIMOXAZOLE_NON = 0;

		public static final int COTRIMOXAZOLE_COTRIMOXAZOLE_960 = 1;

		public static final int COTRIMOXAZOLE_COTRIMOXAZOLE_480 = 2;

		public static final String ANTIRETROVIRAUX = "antiretroviraux";

		public static final String FUMEUR = "fumeur";

		public static final String FUMEURARRETER = "fumeurarreter";

		public static final String EXAMENSMISCROCOPIES = "examensmiscrocopies";

		public static final String EXAMENSATB = "examensatb";

		public static final String REGIMEPHASEINITIALE = "regimephaseinitiale";

		public static final String REGIMEPHASECONTINUATION = "regimephasecontinuation";

		public static final String PRISEMEDICAMENTEUSEPHASEINITIALE = "prisemedicamenteusephaseinitiale";

		public static final String PRISEMEDICAMENTEUSEPHASECONTINUATION = "prisemedicamenteusephasecontinuation";

		public static final String RENDEZVOUS = "rendezvous";

		public static final String DATEFINTRAITEMENT = "datefintraitement";

		public static final String DEVENIRMALADE = "devenirmalade";

		public static final int DEVENIRMALADE_GUERRIS = 0;

		public static final int DEVENIRMALADE_TERMINE = 1;

		public static final int DEVENIRMALADE_ECHEC = 2;

		public static final int DEVENIRMALADE_DECEDE = 3;

		public static final int DEVENIRMALADE_PERDUDEVUE = 4;

		public static final int DEVENIRMALADE_ARRETPRESCRIPTEUR = 5;

		public static final int DEVENIRMALADE_ARRETEFFETSINDESI = 6;

		public static final int DEVENIRMALADE_ARRETSURVENUTB = 7;

		public static final String OBSERVATION = "observation";

	}

	private static final long serialVersionUID = 8249658297344350898L;

	/* Informations group fields */

	private String identifiant;

	private String numRegTB;

	@ManyToOne
	@JoinColumn(name = "casTuberculosePatient_id")
	private Patient patient;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateDebutTraitement;

	private String typePatient;

	@Column(columnDefinition = "TEXT")
	private String typePatientPrecisions;

	private String formeMaladie;

	@Column(columnDefinition = "TEXT")
	private String extraPulmonairePrecisions;

	private String cotrimoxazole;

	private Boolean antiRetroViraux;

	private Boolean fumeur;

	private Boolean fumeurArreter;

	/* Examen group fields */

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = false)
	@JoinColumn(name = "examensMiscrocopiesCasTuberculose_id")
	private List<ExamenMicroscopie> examensMiscrocopies;

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = false)
	@JoinColumn(name = "examensATBCasTuberculose_id")
	private List<ExamenATB> examensATB;

	/* Traitement group fields */

	@ManyToOne
	private Regime regimePhaseInitiale;

	@ManyToOne
	private Regime regimePhaseContinuation;

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = false, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "priseMedicamenteusePhaseInitialeCasTuberculose_id")
	private List<PriseMedicamenteuse> priseMedicamenteusePhaseInitiale;

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = false, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "priseMedicamenteusePhaseContinuationCasTuberculose_id")
	private List<PriseMedicamenteuse> priseMedicamenteusePhaseContinuation;

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = false, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "rendezVousCasTuberculose_id")
	private List<RendezVous> rendezVous;

	/* FinTraitement group fields */

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateFinTraitement;

	private String devenirMalade;

	@Column(columnDefinition = "TEXT")
	private String observation;

	/**
	 * Constructor
	 */
	public CasTuberculose() {
		examensMiscrocopies = new ArrayList<ExamenMicroscopie>();
		examensATB = new ArrayList<ExamenATB>();
		priseMedicamenteusePhaseInitiale = new ArrayList<PriseMedicamenteuse>();
		priseMedicamenteusePhaseContinuation = new ArrayList<PriseMedicamenteuse>();
		rendezVous = new ArrayList<RendezVous>();
	}

	/* Getters and Setters for Informations group fields */

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String value) {
		identifiant = value;
	}

	public String getNumRegTB() {
		return numRegTB;
	}

	public void setNumRegTB(String value) {
		numRegTB = value;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient value) {
		patient = value;
	}

	public Date getDateDebutTraitement() {
		return dateDebutTraitement;
	}

	public void setDateDebutTraitement(Date value) {
		dateDebutTraitement = value;
	}

	public String getTypePatient() {
		return typePatient;
	}

	public void setTypePatient(String value) {
		typePatient = value;
	}

	public String getTypePatientPrecisions() {
		return typePatientPrecisions;
	}

	public void setTypePatientPrecisions(String value) {
		typePatientPrecisions = value;
	}

	public String getFormeMaladie() {
		return formeMaladie;
	}

	public void setFormeMaladie(String value) {
		formeMaladie = value;
	}

	public String getExtraPulmonairePrecisions() {
		return extraPulmonairePrecisions;
	}

	public void setExtraPulmonairePrecisions(String value) {
		extraPulmonairePrecisions = value;
	}

	public String getCotrimoxazole() {
		return cotrimoxazole;
	}

	public void setCotrimoxazole(String value) {
		cotrimoxazole = value;
	}

	public Boolean getAntiRetroViraux() {
		return antiRetroViraux;
	}

	public void setAntiRetroViraux(Boolean value) {
		antiRetroViraux = value;
	}

	public Boolean getFumeur() {
		return fumeur;
	}

	public void setFumeur(Boolean value) {
		fumeur = value;
	}

	public Boolean getFumeurArreter() {
		return fumeurArreter;
	}

	public void setFumeurArreter(Boolean value) {
		fumeurArreter = value;
	}

	/* Getters and Setters for Examen group fields */

	public List<ExamenMicroscopie> getExamensMiscrocopies() {
		return examensMiscrocopies;
	}

	public void setExamensMiscrocopies(List<ExamenMicroscopie> value) {
		examensMiscrocopies = value;
	}

	/**
	 * @param param the ExamenMicroscopie to add to the examensMiscrocopies collection
	 */
	public void addToexamensMiscrocopies(ExamenMicroscopie param) {
		param.setCasTb(this);
		examensMiscrocopies.add(param);
	}

	/**
	 * @param param the ExamenMicroscopie to remove from the examensMiscrocopies collection
	 */
	public void removeFromexamensMiscrocopies(ExamenMicroscopie param) {
		param.setCasTb(null);
		examensMiscrocopies.remove(param);
	}

	public List<ExamenATB> getExamensATB() {
		return examensATB;
	}

	public void setExamensATB(List<ExamenATB> value) {
		examensATB = value;
	}

	/**
	 * @param param the ExamenATB to add to the examensATB collection
	 */
	public void addToexamensATB(ExamenATB param) {
		param.setCasTb(this);
		examensATB.add(param);
	}

	/**
	 * @param param the ExamenATB to remove from the examensATB collection
	 */
	public void removeFromexamensATB(ExamenATB param) {
		param.setCasTb(null);
		examensATB.remove(param);
	}

	/* Getters and Setters for Traitement group fields */

	public Regime getRegimePhaseInitiale() {
		return regimePhaseInitiale;
	}

	public void setRegimePhaseInitiale(Regime value) {
		regimePhaseInitiale = value;
	}

	public Regime getRegimePhaseContinuation() {
		return regimePhaseContinuation;
	}

	public void setRegimePhaseContinuation(Regime value) {
		regimePhaseContinuation = value;
	}

	public List<PriseMedicamenteuse> getPriseMedicamenteusePhaseInitiale() {
		return priseMedicamenteusePhaseInitiale;
	}

	public void setPriseMedicamenteusePhaseInitiale(
			List<PriseMedicamenteuse> value) {
		priseMedicamenteusePhaseInitiale = value;
	}

	/**
	 * @param param the PriseMedicamenteuse to add to the priseMedicamenteusePhaseInitiale collection
	 */
	public void addTopriseMedicamenteusePhaseInitiale(PriseMedicamenteuse param) {
		param.setPhaseIntensive(this);
		priseMedicamenteusePhaseInitiale.add(param);
	}

	/**
	 * @param param the PriseMedicamenteuse to remove from the priseMedicamenteusePhaseInitiale collection
	 */
	public void removeFrompriseMedicamenteusePhaseInitiale(
			PriseMedicamenteuse param) {
		param.setPhaseIntensive(null);
		priseMedicamenteusePhaseInitiale.remove(param);
	}

	public List<PriseMedicamenteuse> getPriseMedicamenteusePhaseContinuation() {
		return priseMedicamenteusePhaseContinuation;
	}

	public void setPriseMedicamenteusePhaseContinuation(
			List<PriseMedicamenteuse> value) {
		priseMedicamenteusePhaseContinuation = value;
	}

	/**
	 * @param param the PriseMedicamenteuse to add to the priseMedicamenteusePhaseContinuation collection
	 */
	public void addTopriseMedicamenteusePhaseContinuation(
			PriseMedicamenteuse param) {
		param.setPhaseContinuation(this);
		priseMedicamenteusePhaseContinuation.add(param);
	}

	/**
	 * @param param the PriseMedicamenteuse to remove from the priseMedicamenteusePhaseContinuation collection
	 */
	public void removeFrompriseMedicamenteusePhaseContinuation(
			PriseMedicamenteuse param) {
		param.setPhaseContinuation(null);
		priseMedicamenteusePhaseContinuation.remove(param);
	}

	public List<RendezVous> getRendezVous() {
		return rendezVous;
	}

	public void setRendezVous(List<RendezVous> value) {
		rendezVous = value;
	}

	/**
	 * @param param the RendezVous to add to the rendezVous collection
	 */
	public void addTorendezVous(RendezVous param) {
		param.setCasTb(this);
		rendezVous.add(param);
	}

	/**
	 * @param param the RendezVous to remove from the rendezVous collection
	 */
	public void removeFromrendezVous(RendezVous param) {
		param.setCasTb(null);
		rendezVous.remove(param);
	}

	/* Getters and Setters for FinTraitement group fields */

	public Date getDateFinTraitement() {
		return dateFinTraitement;
	}

	public void setDateFinTraitement(Date value) {
		dateFinTraitement = value;
	}

	public String getDevenirMalade() {
		return devenirMalade;
	}

	public void setDevenirMalade(String value) {
		devenirMalade = value;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String value) {
		observation = value;
	}

}
