package org.imogene.epicam.server;

import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import org.imogene.epicam.domain.entity.LocalizedText;
import org.imogene.epicam.shared.constants.EpicamEnumConstants;
import org.imogene.lib.common.entity.ImogBean;
import org.imogene.web.server.util.DateUtil;

import org.imogene.epicam.domain.entity.Patient;
import org.imogene.epicam.domain.entity.CasIndex;
import org.imogene.epicam.domain.entity.CasTuberculose;
import org.imogene.epicam.domain.entity.ExamenSerologie;
import org.imogene.epicam.domain.entity.ExamenBiologique;
import org.imogene.epicam.domain.entity.ExamenMicroscopie;
import org.imogene.epicam.domain.entity.ExamenATB;
import org.imogene.epicam.domain.entity.PriseMedicamenteuse;
import org.imogene.epicam.domain.entity.RendezVous;
import org.imogene.epicam.domain.entity.TransfertReference;
import org.imogene.epicam.domain.entity.Lot;
import org.imogene.epicam.domain.entity.HorsUsage;
import org.imogene.epicam.domain.entity.EntreeLot;
import org.imogene.epicam.domain.entity.SortieLot;
import org.imogene.epicam.domain.entity.Commande;
import org.imogene.epicam.domain.entity.DetailCommandeMedicament;
import org.imogene.epicam.domain.entity.DetailCommandeIntrant;
import org.imogene.epicam.domain.entity.Reception;
import org.imogene.epicam.domain.entity.DetailReceptionMedicament;
import org.imogene.epicam.domain.entity.DetailReceptionIntrant;
import org.imogene.epicam.domain.entity.Ravitaillement;
import org.imogene.epicam.domain.entity.DetailRavitaillement;
import org.imogene.epicam.domain.entity.Inventaire;
import org.imogene.epicam.domain.entity.DetailInventaire;
import org.imogene.epicam.domain.entity.Personnel;
import org.imogene.epicam.domain.entity.DepartPersonnel;
import org.imogene.epicam.domain.entity.ArriveePersonnel;
import org.imogene.epicam.domain.entity.Region;
import org.imogene.epicam.domain.entity.DistrictSante;
import org.imogene.epicam.domain.entity.CentreDiagTrait;
import org.imogene.epicam.domain.entity.LaboratoireReference;
import org.imogene.epicam.domain.entity.LieuDit;
import org.imogene.epicam.domain.entity.Regime;
import org.imogene.epicam.domain.entity.PriseMedicamentRegime;
import org.imogene.epicam.domain.entity.Medicament;
import org.imogene.epicam.domain.entity.Intrant;
import org.imogene.epicam.domain.entity.Formation;
import org.imogene.epicam.domain.entity.CandidatureFormation;
import org.imogene.epicam.domain.entity.Qualification;
import org.imogene.epicam.domain.entity.Tutoriel;
import org.imogene.epicam.domain.entity.SmsPredefini;
import org.imogene.epicam.domain.entity.OutBox;
import org.imogene.epicam.domain.entity.Utilisateur;

/**
 * Singleton that enables to render a display value of the beans
 * on the server side
 * @author MEDES-IMPS
 */
public class EpicamServerRenderer {

	private final static String propertyFile = "org.imogene.epicam.client.i18n.EpicamTranslations";
	private static ResourceBundle rb;

	private static EpicamServerRenderer instance = new EpicamServerRenderer();

	private EpicamServerRenderer() {
		rb = ResourceBundle.getBundle(propertyFile);
	}

	public static EpicamServerRenderer get() {
		return instance;
	}

	public void setLocale(String isoCode) {
		if (isoCode != null && !isoCode.isEmpty()) {
			if (isoCode.length() == 2)
				rb = ResourceBundle.getBundle(propertyFile + "_" + isoCode);
			else if (isoCode.length() == 5)
				rb = ResourceBundle.getBundle(propertyFile + "_"
						+ isoCode.substring(0, 2));
		}
	}

	public String getDisplayValue(ImogBean bean, ResourceBundle bundle) {
		if (!rb.equals(bundle)) {
			rb = bundle;
		}
		return getDisplayValue(bean);
	}

	/**
	 * Get display representation for a ImogBean     
	 * @param bean the ImogBean
	 * @return the string representation
	 */
	public String getDisplayValue(ImogBean bean) {

		if (bean instanceof Patient) {
			return getDisplayValue((Patient) bean);
		}
		if (bean instanceof CasIndex) {
			return getDisplayValue((CasIndex) bean);
		}
		if (bean instanceof CasTuberculose) {
			return getDisplayValue((CasTuberculose) bean);
		}
		if (bean instanceof ExamenSerologie) {
			return getDisplayValue((ExamenSerologie) bean);
		}
		if (bean instanceof ExamenBiologique) {
			return getDisplayValue((ExamenBiologique) bean);
		}
		if (bean instanceof ExamenMicroscopie) {
			return getDisplayValue((ExamenMicroscopie) bean);
		}
		if (bean instanceof ExamenATB) {
			return getDisplayValue((ExamenATB) bean);
		}
		if (bean instanceof PriseMedicamenteuse) {
			return getDisplayValue((PriseMedicamenteuse) bean);
		}
		if (bean instanceof RendezVous) {
			return getDisplayValue((RendezVous) bean);
		}
		if (bean instanceof TransfertReference) {
			return getDisplayValue((TransfertReference) bean);
		}
		if (bean instanceof Lot) {
			return getDisplayValue((Lot) bean);
		}
		if (bean instanceof HorsUsage) {
			return getDisplayValue((HorsUsage) bean);
		}
		if (bean instanceof EntreeLot) {
			return getDisplayValue((EntreeLot) bean);
		}
		if (bean instanceof SortieLot) {
			return getDisplayValue((SortieLot) bean);
		}
		if (bean instanceof Commande) {
			return getDisplayValue((Commande) bean);
		}
		if (bean instanceof DetailCommandeMedicament) {
			return getDisplayValue((DetailCommandeMedicament) bean);
		}
		if (bean instanceof DetailCommandeIntrant) {
			return getDisplayValue((DetailCommandeIntrant) bean);
		}
		if (bean instanceof Reception) {
			return getDisplayValue((Reception) bean);
		}
		if (bean instanceof DetailReceptionMedicament) {
			return getDisplayValue((DetailReceptionMedicament) bean);
		}
		if (bean instanceof DetailReceptionIntrant) {
			return getDisplayValue((DetailReceptionIntrant) bean);
		}
		if (bean instanceof Ravitaillement) {
			return getDisplayValue((Ravitaillement) bean);
		}
		if (bean instanceof DetailRavitaillement) {
			return getDisplayValue((DetailRavitaillement) bean);
		}
		if (bean instanceof Inventaire) {
			return getDisplayValue((Inventaire) bean);
		}
		if (bean instanceof DetailInventaire) {
			return getDisplayValue((DetailInventaire) bean);
		}
		if (bean instanceof Personnel) {
			return getDisplayValue((Personnel) bean);
		}
		if (bean instanceof DepartPersonnel) {
			return getDisplayValue((DepartPersonnel) bean);
		}
		if (bean instanceof ArriveePersonnel) {
			return getDisplayValue((ArriveePersonnel) bean);
		}
		if (bean instanceof Region) {
			return getDisplayValue((Region) bean);
		}
		if (bean instanceof DistrictSante) {
			return getDisplayValue((DistrictSante) bean);
		}
		if (bean instanceof CentreDiagTrait) {
			return getDisplayValue((CentreDiagTrait) bean);
		}
		if (bean instanceof LaboratoireReference) {
			return getDisplayValue((LaboratoireReference) bean);
		}
		if (bean instanceof LieuDit) {
			return getDisplayValue((LieuDit) bean);
		}
		if (bean instanceof Regime) {
			return getDisplayValue((Regime) bean);
		}
		if (bean instanceof PriseMedicamentRegime) {
			return getDisplayValue((PriseMedicamentRegime) bean);
		}
		if (bean instanceof Medicament) {
			return getDisplayValue((Medicament) bean);
		}
		if (bean instanceof Intrant) {
			return getDisplayValue((Intrant) bean);
		}
		if (bean instanceof Formation) {
			return getDisplayValue((Formation) bean);
		}
		if (bean instanceof CandidatureFormation) {
			return getDisplayValue((CandidatureFormation) bean);
		}
		if (bean instanceof Qualification) {
			return getDisplayValue((Qualification) bean);
		}
		if (bean instanceof Tutoriel) {
			return getDisplayValue((Tutoriel) bean);
		}
		if (bean instanceof SmsPredefini) {
			return getDisplayValue((SmsPredefini) bean);
		}
		if (bean instanceof OutBox) {
			return getDisplayValue((OutBox) bean);
		}
		if (bean instanceof Utilisateur) {
			return getDisplayValue((Utilisateur) bean);
		}
		return "";
	}

	/**	 */
	public String getDisplayValue(Patient bean) {
		String value = "";
		if (bean.getIdentifiant() != null) {
			value = value + bean.getIdentifiant() + " ";
		}
		if (bean.getNom() != null) {
			value = value + bean.getNom() + " ";
		}

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(CasIndex bean) {
		String value = "";

		if (bean.getPatient() != null)
			value = value + getDisplayValue(bean.getPatient()) + " ";

		if (bean.getTypeRelation() != null) {
			value = value + bean.getTypeRelation() + " ";
		}

		if (bean.getPatientLie() != null)
			value = value + getDisplayValue(bean.getPatientLie()) + " ";

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(CasTuberculose bean) {
		String value = "";

		if (bean.getPatient() != null)
			value = value + getDisplayValue(bean.getPatient()) + " ";

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(ExamenSerologie bean) {
		String value = "";
		if (bean.getDateTest() != null)
			value = value + DateUtil.getFormatedDate(bean.getDateTest()) + " ";
		String nature = bean.getNature();
		if (nature != null) {
			if (nature.equals(EpicamEnumConstants.EXAMENSEROLOGIE_NATURE_VIH))
				value = value
						+ rb.getString("examenSerologie_nature_vIH_option")
						+ " ";
			else if (nature
					.equals(EpicamEnumConstants.EXAMENSEROLOGIE_NATURE_CD4))
				value = value
						+ rb.getString("examenSerologie_nature_cD4_option")
						+ " ";
		}
		if (bean.getResultatCD4() != null)
			value = value + bean.getResultatCD4() + " ";
		String resultatVIH = bean.getResultatVIH();
		if (resultatVIH != null) {
			if (resultatVIH
					.equals(EpicamEnumConstants.EXAMENSEROLOGIE_RESULTATVIH_POSITIF))
				value = value
						+ rb.getString("examenSerologie_resultatVIH_positif_option")
						+ " ";
			else if (resultatVIH
					.equals(EpicamEnumConstants.EXAMENSEROLOGIE_RESULTATVIH_NEGATIF))
				value = value
						+ rb.getString("examenSerologie_resultatVIH_negatif_option")
						+ " ";
		}

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(ExamenBiologique bean) {
		String value = "";
		if (bean.getDate() != null)
			value = value + DateUtil.getFormatedDate(bean.getDate()) + " ";
		if (bean.getPoids() != null)
			value = value + bean.getPoids() + " ";

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(ExamenMicroscopie bean) {
		String value = "";
		if (bean.getDate() != null)
			value = value + DateUtil.getFormatedDate(bean.getDate()) + " ";
		String resultat = bean.getResultat();
		if (resultat != null) {
			if (resultat
					.equals(EpicamEnumConstants.EXAMENMICROSCOPIE_RESULTAT_NEGATIF))
				value = value
						+ rb.getString("examenMicroscopie_resultat_negatif_option")
						+ " ";
			else if (resultat
					.equals(EpicamEnumConstants.EXAMENMICROSCOPIE_RESULTAT_RARE))
				value = value
						+ rb.getString("examenMicroscopie_resultat_rare_option")
						+ " ";
			else if (resultat
					.equals(EpicamEnumConstants.EXAMENMICROSCOPIE_RESULTAT_UNPLUS))
				value = value
						+ rb.getString("examenMicroscopie_resultat_unPlus_option")
						+ " ";
			else if (resultat
					.equals(EpicamEnumConstants.EXAMENMICROSCOPIE_RESULTAT_DEUXPLUS))
				value = value
						+ rb.getString("examenMicroscopie_resultat_deuxPlus_option")
						+ " ";
			else if (resultat
					.equals(EpicamEnumConstants.EXAMENMICROSCOPIE_RESULTAT_TROISPLUS))
				value = value
						+ rb.getString("examenMicroscopie_resultat_troisPlus_option")
						+ " ";
		}

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(ExamenATB bean) {
		String value = "";
		if (bean.getDateExamen() != null)
			value = value + DateUtil.getFormatedDate(bean.getDateExamen())
					+ " ";
		if (bean.getResultat() != null) {
			value = value + bean.getResultat() + " ";
		}

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(PriseMedicamenteuse bean) {
		String value = "";
		if (bean.getDateEffective() != null)
			value = value + DateUtil.getFormatedDate(bean.getDateEffective())
					+ " ";

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(RendezVous bean) {
		String value = "";
		if (bean.getDateRendezVous() != null)
			value = value + DateUtil.getFormatedDate(bean.getDateRendezVous())
					+ " ";

		value = value + getBooleanDisplayValue(bean.getHonore()) + " ";

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(TransfertReference bean) {
		String value = "";

		if (bean.getPatient() != null)
			value = value + getDisplayValue(bean.getPatient()) + " ";

		String nature = bean.getNature();
		if (nature != null) {
			if (nature
					.equals(EpicamEnumConstants.TRANSFERTREFERENCE_NATURE_TRANSFERT))
				value = value
						+ rb.getString("transfertReference_nature_transfert_option")
						+ " ";
			else if (nature
					.equals(EpicamEnumConstants.TRANSFERTREFERENCE_NATURE_REFERENCE))
				value = value
						+ rb.getString("transfertReference_nature_reference_option")
						+ " ";
		}
		if (bean.getDateDepart() != null)
			value = value + DateUtil.getFormatedDate(bean.getDateDepart())
					+ " ";

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(Lot bean) {
		String value = "";
		if (bean.getNumero() != null) {
			value = value + bean.getNumero() + " ";
		}

		if (bean.getIntrant() != null)
			value = value + getDisplayValue(bean.getIntrant()) + " ";

		if (bean.getMedicament() != null)
			value = value + getDisplayValue(bean.getMedicament()) + " ";

		if (bean.getQuantite() != null)
			value = value + bean.getQuantite() + " ";

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(HorsUsage bean) {
		String value = "";
		String type = bean.getType();
		if (type != null) {
			if (type.equals(EpicamEnumConstants.HORSUSAGE_TYPE_PERIMEE))
				value = value + rb.getString("horsUsage_type_perimee_option")
						+ " ";
			else if (type.equals(EpicamEnumConstants.HORSUSAGE_TYPE_CASSE))
				value = value + rb.getString("horsUsage_type_casse_option")
						+ " ";
		}

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(EntreeLot bean) {
		String value = "";

		if (bean.getLot() != null)
			value = value + getDisplayValue(bean.getLot()) + " ";

		if (bean.getQuantite() != null)
			value = value + bean.getQuantite() + " ";

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(SortieLot bean) {
		String value = "";

		if (bean.getLot() != null)
			value = value + getDisplayValue(bean.getLot()) + " ";

		if (bean.getQuantite() != null)
			value = value + bean.getQuantite() + " ";

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(Commande bean) {
		String value = "";

		if (bean.getCDT() != null)
			value = value + getDisplayValue(bean.getCDT()) + " ";

		if (bean.getDate() != null)
			value = value + DateUtil.getFormatedDate(bean.getDate()) + " ";

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(DetailCommandeMedicament bean) {
		String value = "";

		if (bean.getMedicament() != null)
			value = value + getDisplayValue(bean.getMedicament()) + " ";

		if (bean.getQuantiteRequise() != null)
			value = value + bean.getQuantiteRequise() + " ";

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(DetailCommandeIntrant bean) {
		String value = "";

		if (bean.getIntrant() != null)
			value = value + getDisplayValue(bean.getIntrant()) + " ";

		if (bean.getQuantiteRequise() != null)
			value = value + bean.getQuantiteRequise() + " ";

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(Reception bean) {
		String value = "";

		if (bean.getCDT() != null)
			value = value + getDisplayValue(bean.getCDT()) + " ";

		if (bean.getDateReception() != null)
			value = value + DateUtil.getFormatedDate(bean.getDateReception())
					+ " ";

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(DetailReceptionMedicament bean) {
		String value = "";

		if (bean.getDetailCommande() != null)
			value = value + getDisplayValue(bean.getDetailCommande()) + " ";

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(DetailReceptionIntrant bean) {
		String value = "";

		if (bean.getDetailCommande() != null)
			value = value + getDisplayValue(bean.getDetailCommande()) + " ";

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(Ravitaillement bean) {
		String value = "";

		if (bean.getCDTDepart() != null)
			value = value + getDisplayValue(bean.getCDTDepart()) + " ";

		if (bean.getDateDepart() != null)
			value = value + DateUtil.getFormatedDate(bean.getDateDepart())
					+ " ";

		if (bean.getCDTArrivee() != null)
			value = value + getDisplayValue(bean.getCDTArrivee()) + " ";

		if (bean.getDateArrivee() != null)
			value = value + DateUtil.getFormatedDate(bean.getDateArrivee())
					+ " ";

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(DetailRavitaillement bean) {
		String value = "";

		if (bean.getRavitaillement() != null)
			value = value + getDisplayValue(bean.getRavitaillement()) + " ";

		if (bean.getSortieLot() != null)
			value = value + getDisplayValue(bean.getSortieLot()) + " ";

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(Inventaire bean) {
		String value = "";

		if (bean.getCDT() != null)
			value = value + getDisplayValue(bean.getCDT()) + " ";

		if (bean.getDate() != null)
			value = value + DateUtil.getFormatedDate(bean.getDate()) + " ";

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(DetailInventaire bean) {
		String value = "";

		if (bean.getLot() != null)
			value = value + getDisplayValue(bean.getLot()) + " ";

		if (bean.getQuantiteTheorique() != null)
			value = value + bean.getQuantiteTheorique() + " ";

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(Personnel bean) {
		String value = "";
		if (bean.getNom() != null) {
			value = value + bean.getNom() + " ";
		}

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(DepartPersonnel bean) {
		String value = "";
		if (bean.getDateDepart() != null)
			value = value + DateUtil.getFormatedDate(bean.getDateDepart())
					+ " ";

		if (bean.getPersonnel() != null)
			value = value + getDisplayValue(bean.getPersonnel()) + " ";

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(ArriveePersonnel bean) {
		String value = "";
		if (bean.getDateArrivee() != null)
			value = value + DateUtil.getFormatedDate(bean.getDateArrivee())
					+ " ";

		if (bean.getPersonnel() != null)
			value = value + getDisplayValue(bean.getPersonnel()) + " ";

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(Region bean) {
		String value = "";
		if (bean.getNom() != null) {
			value = value + getLocalizedText(bean.getNom()) + " ";
		}

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(DistrictSante bean) {
		String value = "";
		if (bean.getNom() != null) {
			value = value + getLocalizedText(bean.getNom()) + " ";
		}

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(CentreDiagTrait bean) {
		String value = "";
		if (bean.getNom() != null) {
			value = value + bean.getNom() + " ";
		}

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(LaboratoireReference bean) {
		String value = "";
		if (bean.getNom() != null) {
			value = value + getLocalizedText(bean.getNom()) + " ";
		}

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(LieuDit bean) {
		String value = "";
		if (bean.getNom() != null) {
			value = value + bean.getNom() + " ";
		}

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(Regime bean) {
		String value = "";
		if (bean.getNom() != null) {
			value = value + bean.getNom() + " ";
		}

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(PriseMedicamentRegime bean) {
		String value = "";

		if (bean.getMedicament() != null)
			value = value + getDisplayValue(bean.getMedicament()) + " ";

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(Medicament bean) {
		String value = "";
		if (bean.getDesignation() != null) {
			value = value + bean.getDesignation() + " ";
		}

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(Intrant bean) {
		String value = "";
		if (bean.getIdentifiant() != null) {
			value = value + bean.getIdentifiant() + " ";
		}

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(Formation bean) {
		String value = "";
		if (bean.getLibelle() != null) {
			value = value + getLocalizedText(bean.getLibelle()) + " ";
		}
		if (bean.getDateDebut() != null)
			value = value + DateUtil.getFormatedDate(bean.getDateDebut()) + " ";

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(CandidatureFormation bean) {
		String value = "";

		if (bean.getPersonnel() != null)
			value = value + getDisplayValue(bean.getPersonnel()) + " ";

		value = value + getBooleanDisplayValue(bean.getApprouveeRegion()) + " ";

		value = value + getBooleanDisplayValue(bean.getApprouveeGTC()) + " ";

		if (bean.getDistrictSante() != null)
			value = value + getDisplayValue(bean.getDistrictSante()) + " ";

		if (bean.getCDT() != null)
			value = value + getDisplayValue(bean.getCDT()) + " ";

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(Qualification bean) {
		String value = "";
		if (bean.getNom() != null) {
			value = value + getLocalizedText(bean.getNom()) + " ";
		}

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(Tutoriel bean) {
		String value = "";
		if (bean.getNom() != null) {
			value = value + getLocalizedText(bean.getNom()) + " ";
		}

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(SmsPredefini bean) {
		String value = "";
		String type = bean.getType();
		if (type != null) {
			if (type.equals(EpicamEnumConstants.SMSPREDEFINI_TYPE_SENSIBILISATION))
				value = value
						+ rb.getString("smsPredefini_type_sensibilisation_option")
						+ " ";
			else if (type.equals(EpicamEnumConstants.SMSPREDEFINI_TYPE_QUIZZ))
				value = value + rb.getString("smsPredefini_type_quizz_option")
						+ " ";
			else if (type
					.equals(EpicamEnumConstants.SMSPREDEFINI_TYPE_RAPPELRDV))
				value = value
						+ rb.getString("smsPredefini_type_rappelRDV_option")
						+ " ";
			else if (type
					.equals(EpicamEnumConstants.SMSPREDEFINI_TYPE_MEDICALRECORD))
				value = value
						+ rb.getString("smsPredefini_type_medicalRecord_option")
						+ " ";
		}
		if (bean.getObjet() != null) {
			value = value + getLocalizedText(bean.getObjet()) + " ";
		}

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(OutBox bean) {
		String value = "";
		if (bean.getMessage() != null) {
			value = value + bean.getMessage() + " ";
		}

		return value.trim();
	}
	/**	 */
	public String getDisplayValue(Utilisateur bean) {
		String value = "";
		if (bean.getNom() != null) {
			value = value + bean.getNom() + " ";
		}

		return value.trim();
	}

	/**
	 * Get an enumeration representation for a ImogBean type enumeration field
	 * @param beanClass a ImogBean class type
	 * @param fieldName the ImogBean field name     
	 * @param fieldValue the bean field value    
	 * @return the enumeration string representation
	 */
	public String getEnumDisplayValue(Class<?> beanClass, String fieldName,
			String fieldValue) {

		if (fieldValue != null && !fieldValue.equals("")) {

			if (beanClass.equals(Patient.class)) {
				return getPatientEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(CasIndex.class)) {
				return getCasIndexEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(CasTuberculose.class)) {
				return getCasTuberculoseEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(ExamenSerologie.class)) {
				return getExamenSerologieEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(ExamenBiologique.class)) {
				return getExamenBiologiqueEnumDisplayValue(fieldName,
						fieldValue);
			}

			if (beanClass.equals(ExamenMicroscopie.class)) {
				return getExamenMicroscopieEnumDisplayValue(fieldName,
						fieldValue);
			}

			if (beanClass.equals(ExamenATB.class)) {
				return getExamenATBEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(PriseMedicamenteuse.class)) {
				return getPriseMedicamenteuseEnumDisplayValue(fieldName,
						fieldValue);
			}

			if (beanClass.equals(RendezVous.class)) {
				return getRendezVousEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(TransfertReference.class)) {
				return getTransfertReferenceEnumDisplayValue(fieldName,
						fieldValue);
			}

			if (beanClass.equals(Lot.class)) {
				return getLotEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(HorsUsage.class)) {
				return getHorsUsageEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(EntreeLot.class)) {
				return getEntreeLotEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(SortieLot.class)) {
				return getSortieLotEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(Commande.class)) {
				return getCommandeEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(DetailCommandeMedicament.class)) {
				return getDetailCommandeMedicamentEnumDisplayValue(fieldName,
						fieldValue);
			}

			if (beanClass.equals(DetailCommandeIntrant.class)) {
				return getDetailCommandeIntrantEnumDisplayValue(fieldName,
						fieldValue);
			}

			if (beanClass.equals(Reception.class)) {
				return getReceptionEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(DetailReceptionMedicament.class)) {
				return getDetailReceptionMedicamentEnumDisplayValue(fieldName,
						fieldValue);
			}

			if (beanClass.equals(DetailReceptionIntrant.class)) {
				return getDetailReceptionIntrantEnumDisplayValue(fieldName,
						fieldValue);
			}

			if (beanClass.equals(Ravitaillement.class)) {
				return getRavitaillementEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(DetailRavitaillement.class)) {
				return getDetailRavitaillementEnumDisplayValue(fieldName,
						fieldValue);
			}

			if (beanClass.equals(Inventaire.class)) {
				return getInventaireEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(DetailInventaire.class)) {
				return getDetailInventaireEnumDisplayValue(fieldName,
						fieldValue);
			}

			if (beanClass.equals(Personnel.class)) {
				return getPersonnelEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(DepartPersonnel.class)) {
				return getDepartPersonnelEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(ArriveePersonnel.class)) {
				return getArriveePersonnelEnumDisplayValue(fieldName,
						fieldValue);
			}

			if (beanClass.equals(Region.class)) {
				return getRegionEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(DistrictSante.class)) {
				return getDistrictSanteEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(CentreDiagTrait.class)) {
				return getCentreDiagTraitEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(LaboratoireReference.class)) {
				return getLaboratoireReferenceEnumDisplayValue(fieldName,
						fieldValue);
			}

			if (beanClass.equals(LieuDit.class)) {
				return getLieuDitEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(Regime.class)) {
				return getRegimeEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(PriseMedicamentRegime.class)) {
				return getPriseMedicamentRegimeEnumDisplayValue(fieldName,
						fieldValue);
			}

			if (beanClass.equals(Medicament.class)) {
				return getMedicamentEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(Intrant.class)) {
				return getIntrantEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(Formation.class)) {
				return getFormationEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(CandidatureFormation.class)) {
				return getCandidatureFormationEnumDisplayValue(fieldName,
						fieldValue);
			}

			if (beanClass.equals(Qualification.class)) {
				return getQualificationEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(Tutoriel.class)) {
				return getTutorielEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(SmsPredefini.class)) {
				return getSmsPredefiniEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(OutBox.class)) {
				return getOutBoxEnumDisplayValue(fieldName, fieldValue);
			}

			if (beanClass.equals(Utilisateur.class)) {
				return getUtilisateurEnumDisplayValue(fieldName, fieldValue);
			}

		}
		return "";
	}

	/**
	 *
	 */
	public String getPatientEnumDisplayValue(String fieldName, String fieldValue) {
		String value = "";

		if (fieldName.equals("sexe")) {

			if (fieldValue.equals(EpicamEnumConstants.PATIENT_SEXE_MASCULIN))
				value = rb.getString("patient_sexe_masculin_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.PATIENT_SEXE_FEMININ))
				value = rb.getString("patient_sexe_feminin_option");

		}

		if (fieldName.equals("nationalite")) {

			if (fieldValue
					.equals(EpicamEnumConstants.PATIENT_NATIONALITE_CAMEROUNAIS))
				value = rb.getString("patient_nationalite_camerounais_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.PATIENT_NATIONALITE_ETRANGER))
				value = rb.getString("patient_nationalite_etranger_option");

		}

		if (fieldName.equals("libelle")) {

			if (fieldValue.equals(EpicamEnumConstants.PATIENT_LIBELLE_DOMICILE))
				value = rb.getString("patient_libelle_domicile_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.PATIENT_LIBELLE_BUREAU))
				value = rb.getString("patient_libelle_bureau_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.PATIENT_LIBELLE_AUTRE))
				value = rb.getString("patient_libelle_autre_option");

		}

		if (fieldName.equals("pacLibelle")) {

			if (fieldValue
					.equals(EpicamEnumConstants.PATIENT_PACLIBELLE_DOMICILE))
				value = rb.getString("patient_pacLibelle_domicile_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.PATIENT_PACLIBELLE_BUREAU))
				value = rb.getString("patient_pacLibelle_bureau_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.PATIENT_PACLIBELLE_AUTRE))
				value = rb.getString("patient_pacLibelle_autre_option");

		}

		return value.trim();
	}

	/**
	 *
	 */
	public String getCasIndexEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		return value.trim();
	}

	/**
	 *
	 */
	public String getCasTuberculoseEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		if (fieldName.equals("typePatient")) {

			if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_TYPEPATIENT_NOUVEAUCAS))
				value = rb
						.getString("casTuberculose_typePatient_nouveauCas_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_TYPEPATIENT_REPRISEAPRESABANDON))
				value = rb
						.getString("casTuberculose_typePatient_repriseApresAbandon_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_TYPEPATIENT_ECHEC))
				value = rb.getString("casTuberculose_typePatient_echec_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_TYPEPATIENT_RECHUTE))
				value = rb
						.getString("casTuberculose_typePatient_rechute_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_TYPEPATIENT_AUTRE))
				value = rb.getString("casTuberculose_typePatient_autre_option");

		}

		if (fieldName.equals("formeMaladie")) {

			if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_FORMEMALADIE_TPMPLUS))
				value = rb
						.getString("casTuberculose_formeMaladie_tPMPlus_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_FORMEMALADIE_TPMMOINS))
				value = rb
						.getString("casTuberculose_formeMaladie_tPMMoins_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_FORMEMALADIE_EXTRA_PULMONAIRE))
				value = rb
						.getString("casTuberculose_formeMaladie_extra_Pulmonaire_option");

		}

		if (fieldName.equals("cotrimoxazole")) {

			if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_COTRIMOXAZOLE_NON))
				value = rb.getString("casTuberculose_cotrimoxazole_non_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_COTRIMOXAZOLE_COTRIMOXAZOLE_960))
				value = rb
						.getString("casTuberculose_cotrimoxazole_cotrimoxazole_960_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_COTRIMOXAZOLE_COTRIMOXAZOLE_480))
				value = rb
						.getString("casTuberculose_cotrimoxazole_cotrimoxazole_480_option");

		}

		if (fieldName.equals("devenirMalade")) {

			if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_DEVENIRMALADE_GUERRIS))
				value = rb
						.getString("casTuberculose_devenirMalade_guerris_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_DEVENIRMALADE_TERMINE))
				value = rb
						.getString("casTuberculose_devenirMalade_termine_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_DEVENIRMALADE_ECHEC))
				value = rb
						.getString("casTuberculose_devenirMalade_echec_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_DEVENIRMALADE_DECEDE))
				value = rb
						.getString("casTuberculose_devenirMalade_decede_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_DEVENIRMALADE_PERDUDEVUE))
				value = rb
						.getString("casTuberculose_devenirMalade_perduDeVue_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_DEVENIRMALADE_ARRETPRESCRIPTEUR))
				value = rb
						.getString("casTuberculose_devenirMalade_arretPrescripteur_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_DEVENIRMALADE_ARRETEFFETSINDESI))
				value = rb
						.getString("casTuberculose_devenirMalade_arretEffetsIndesi_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.CASTUBERCULOSE_DEVENIRMALADE_ARRETSURVENUTB))
				value = rb
						.getString("casTuberculose_devenirMalade_arretSurvenuTB_option");

		}

		return value.trim();
	}

	/**
	 *
	 */
	public String getExamenSerologieEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		if (fieldName.equals("nature")) {

			if (fieldValue
					.equals(EpicamEnumConstants.EXAMENSEROLOGIE_NATURE_VIH))
				value = rb.getString("examenSerologie_nature_vIH_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.EXAMENSEROLOGIE_NATURE_CD4))
				value = rb.getString("examenSerologie_nature_cD4_option");

		}

		if (fieldName.equals("resultatVIH")) {

			if (fieldValue
					.equals(EpicamEnumConstants.EXAMENSEROLOGIE_RESULTATVIH_POSITIF))
				value = rb
						.getString("examenSerologie_resultatVIH_positif_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.EXAMENSEROLOGIE_RESULTATVIH_NEGATIF))
				value = rb
						.getString("examenSerologie_resultatVIH_negatif_option");

		}

		return value.trim();
	}

	/**
	 *
	 */
	public String getExamenBiologiqueEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		return value.trim();
	}

	/**
	 *
	 */
	public String getExamenMicroscopieEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		if (fieldName.equals("raisonDepistage")) {

			if (fieldValue
					.equals(EpicamEnumConstants.EXAMENMICROSCOPIE_RAISONDEPISTAGE_DIAGNOSTIC))
				value = rb
						.getString("examenMicroscopie_raisonDepistage_diagnostic_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.EXAMENMICROSCOPIE_RAISONDEPISTAGE_SUIVI))
				value = rb
						.getString("examenMicroscopie_raisonDepistage_suivi_option");

		}

		if (fieldName.equals("resultat")) {

			if (fieldValue
					.equals(EpicamEnumConstants.EXAMENMICROSCOPIE_RESULTAT_NEGATIF))
				value = rb
						.getString("examenMicroscopie_resultat_negatif_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.EXAMENMICROSCOPIE_RESULTAT_RARE))
				value = rb.getString("examenMicroscopie_resultat_rare_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.EXAMENMICROSCOPIE_RESULTAT_UNPLUS))
				value = rb
						.getString("examenMicroscopie_resultat_unPlus_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.EXAMENMICROSCOPIE_RESULTAT_DEUXPLUS))
				value = rb
						.getString("examenMicroscopie_resultat_deuxPlus_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.EXAMENMICROSCOPIE_RESULTAT_TROISPLUS))
				value = rb
						.getString("examenMicroscopie_resultat_troisPlus_option");

		}

		return value.trim();
	}

	/**
	 *
	 */
	public String getExamenATBEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		if (fieldName.equals("raisonDepistage")) {

			if (fieldValue
					.equals(EpicamEnumConstants.EXAMENATB_RAISONDEPISTAGE_DIAGNOSTIC))
				value = rb
						.getString("examenATB_raisonDepistage_diagnostic_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.EXAMENATB_RAISONDEPISTAGE_SUIVI))
				value = rb.getString("examenATB_raisonDepistage_suivi_option");

		}

		return value.trim();
	}

	/**
	 *
	 */
	public String getPriseMedicamenteuseEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		if (fieldName.equals("prise")) {

			if (fieldValue
					.equals(EpicamEnumConstants.PRISEMEDICAMENTEUSE_PRISE_PRISESUPERVISEE))
				value = rb
						.getString("priseMedicamenteuse_prise_priseSupervisee_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.PRISEMEDICAMENTEUSE_PRISE_AUTOMEDICATION))
				value = rb
						.getString("priseMedicamenteuse_prise_automedication_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.PRISEMEDICAMENTEUSE_PRISE_NONVENU))
				value = rb
						.getString("priseMedicamenteuse_prise_nonVenu_option");

		}

		return value.trim();
	}

	/**
	 *
	 */
	public String getRendezVousEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		return value.trim();
	}

	/**
	 *
	 */
	public String getTransfertReferenceEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		if (fieldName.equals("nature")) {

			if (fieldValue
					.equals(EpicamEnumConstants.TRANSFERTREFERENCE_NATURE_TRANSFERT))
				value = rb
						.getString("transfertReference_nature_transfert_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.TRANSFERTREFERENCE_NATURE_REFERENCE))
				value = rb
						.getString("transfertReference_nature_reference_option");

		}

		return value.trim();
	}

	/**
	 *
	 */
	public String getLotEnumDisplayValue(String fieldName, String fieldValue) {
		String value = "";

		if (fieldName.equals("type")) {

			if (fieldValue.equals(EpicamEnumConstants.LOT_TYPE_MEDICAMENT))
				value = rb.getString("lot_type_medicament_option");
			else if (fieldValue.equals(EpicamEnumConstants.LOT_TYPE_INTRANT))
				value = rb.getString("lot_type_intrant_option");

		}

		return value.trim();
	}

	/**
	 *
	 */
	public String getHorsUsageEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		if (fieldName.equals("type")) {

			if (fieldValue.equals(EpicamEnumConstants.HORSUSAGE_TYPE_PERIMEE))
				value = rb.getString("horsUsage_type_perimee_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.HORSUSAGE_TYPE_CASSE))
				value = rb.getString("horsUsage_type_casse_option");

		}

		return value.trim();
	}

	/**
	 *
	 */
	public String getEntreeLotEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		return value.trim();
	}

	/**
	 *
	 */
	public String getSortieLotEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		return value.trim();
	}

	/**
	 *
	 */
	public String getCommandeEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		return value.trim();
	}

	/**
	 *
	 */
	public String getDetailCommandeMedicamentEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		return value.trim();
	}

	/**
	 *
	 */
	public String getDetailCommandeIntrantEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		return value.trim();
	}

	/**
	 *
	 */
	public String getReceptionEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		return value.trim();
	}

	/**
	 *
	 */
	public String getDetailReceptionMedicamentEnumDisplayValue(
			String fieldName, String fieldValue) {
		String value = "";

		return value.trim();
	}

	/**
	 *
	 */
	public String getDetailReceptionIntrantEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		return value.trim();
	}

	/**
	 *
	 */
	public String getRavitaillementEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		return value.trim();
	}

	/**
	 *
	 */
	public String getDetailRavitaillementEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		return value.trim();
	}

	/**
	 *
	 */
	public String getInventaireEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		return value.trim();
	}

	/**
	 *
	 */
	public String getDetailInventaireEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		return value.trim();
	}

	/**
	 *
	 */
	public String getPersonnelEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		if (fieldName.equals("libelle")) {

			if (fieldValue
					.equals(EpicamEnumConstants.PERSONNEL_LIBELLE_DOMICILE))
				value = rb.getString("personnel_libelle_domicile_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.PERSONNEL_LIBELLE_BUREAU))
				value = rb.getString("personnel_libelle_bureau_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.PERSONNEL_LIBELLE_AUTRE))
				value = rb.getString("personnel_libelle_autre_option");

		}

		if (fieldName.equals("niveau")) {

			if (fieldValue.equals(EpicamEnumConstants.PERSONNEL_NIVEAU_CENTRAL))
				value = rb.getString("personnel_niveau_central_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.PERSONNEL_NIVEAU_REGION))
				value = rb.getString("personnel_niveau_region_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.PERSONNEL_NIVEAU_DISTRICTSANTE))
				value = rb.getString("personnel_niveau_districtSante_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.PERSONNEL_NIVEAU_CDT))
				value = rb.getString("personnel_niveau_cDT_option");

		}

		return value.trim();
	}

	/**
	 *
	 */
	public String getDepartPersonnelEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		return value.trim();
	}

	/**
	 *
	 */
	public String getArriveePersonnelEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		return value.trim();
	}

	/**
	 *
	 */
	public String getRegionEnumDisplayValue(String fieldName, String fieldValue) {
		String value = "";

		if (fieldName.equals("libelle")) {

			if (fieldValue.equals(EpicamEnumConstants.REGION_LIBELLE_DOMICILE))
				value = rb.getString("region_libelle_domicile_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.REGION_LIBELLE_BUREAU))
				value = rb.getString("region_libelle_bureau_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.REGION_LIBELLE_AUTRE))
				value = rb.getString("region_libelle_autre_option");

		}

		return value.trim();
	}

	/**
	 *
	 */
	public String getDistrictSanteEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		if (fieldName.equals("libelle")) {

			if (fieldValue
					.equals(EpicamEnumConstants.DISTRICTSANTE_LIBELLE_DOMICILE))
				value = rb.getString("districtSante_libelle_domicile_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.DISTRICTSANTE_LIBELLE_BUREAU))
				value = rb.getString("districtSante_libelle_bureau_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.DISTRICTSANTE_LIBELLE_AUTRE))
				value = rb.getString("districtSante_libelle_autre_option");

		}

		return value.trim();
	}

	/**
	 *
	 */
	public String getCentreDiagTraitEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		if (fieldName.equals("libelle")) {

			if (fieldValue
					.equals(EpicamEnumConstants.CENTREDIAGTRAIT_LIBELLE_DOMICILE))
				value = rb.getString("centreDiagTrait_libelle_domicile_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.CENTREDIAGTRAIT_LIBELLE_BUREAU))
				value = rb.getString("centreDiagTrait_libelle_bureau_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.CENTREDIAGTRAIT_LIBELLE_AUTRE))
				value = rb.getString("centreDiagTrait_libelle_autre_option");

		}

		return value.trim();
	}

	/**
	 *
	 */
	public String getLaboratoireReferenceEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		if (fieldName.equals("nature")) {

			if (fieldValue
					.equals(EpicamEnumConstants.LABORATOIREREFERENCE_NATURE_NATIONAL))
				value = rb
						.getString("laboratoireReference_nature_national_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.LABORATOIREREFERENCE_NATURE_REGIONAL))
				value = rb
						.getString("laboratoireReference_nature_regional_option");

		}

		if (fieldName.equals("libelle")) {

			if (fieldValue
					.equals(EpicamEnumConstants.LABORATOIREREFERENCE_LIBELLE_DOMICILE))
				value = rb
						.getString("laboratoireReference_libelle_domicile_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.LABORATOIREREFERENCE_LIBELLE_BUREAU))
				value = rb
						.getString("laboratoireReference_libelle_bureau_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.LABORATOIREREFERENCE_LIBELLE_AUTRE))
				value = rb
						.getString("laboratoireReference_libelle_autre_option");

		}

		return value.trim();
	}

	/**
	 *
	 */
	public String getLieuDitEnumDisplayValue(String fieldName, String fieldValue) {
		String value = "";

		if (fieldName.equals("libelle")) {

			if (fieldValue.equals(EpicamEnumConstants.LIEUDIT_LIBELLE_DOMICILE))
				value = rb.getString("lieuDit_libelle_domicile_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.LIEUDIT_LIBELLE_BUREAU))
				value = rb.getString("lieuDit_libelle_bureau_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.LIEUDIT_LIBELLE_AUTRE))
				value = rb.getString("lieuDit_libelle_autre_option");

		}

		return value.trim();
	}

	/**
	 *
	 */
	public String getRegimeEnumDisplayValue(String fieldName, String fieldValue) {
		String value = "";

		if (fieldName.equals("type")) {

			if (fieldValue
					.equals(EpicamEnumConstants.REGIME_TYPE_PHASEINITIALE))
				value = rb.getString("regime_type_phaseInitiale_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.REGIME_TYPE_PHASECONTINUATION))
				value = rb.getString("regime_type_phaseContinuation_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.REGIME_TYPE_INDEPENDANT))
				value = rb.getString("regime_type_independant_option");

		}

		return value.trim();
	}

	/**
	 *
	 */
	public String getPriseMedicamentRegimeEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		return value.trim();
	}

	/**
	 *
	 */
	public String getMedicamentEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		return value.trim();
	}

	/**
	 *
	 */
	public String getIntrantEnumDisplayValue(String fieldName, String fieldValue) {
		String value = "";

		return value.trim();
	}

	/**
	 *
	 */
	public String getFormationEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		return value.trim();
	}

	/**
	 *
	 */
	public String getCandidatureFormationEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		return value.trim();
	}

	/**
	 *
	 */
	public String getQualificationEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		return value.trim();
	}

	/**
	 *
	 */
	public String getTutorielEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		if (fieldName.equals("type")) {

			if (fieldValue.equals(EpicamEnumConstants.TUTORIEL_TYPE_TEXTE))
				value = rb.getString("tutoriel_type_texte_option");
			else if (fieldValue.equals(EpicamEnumConstants.TUTORIEL_TYPE_AUDIO))
				value = rb.getString("tutoriel_type_audio_option");
			else if (fieldValue.equals(EpicamEnumConstants.TUTORIEL_TYPE_VIDEO))
				value = rb.getString("tutoriel_type_video_option");

		}

		return value.trim();
	}

	/**
	 *
	 */
	public String getSmsPredefiniEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		if (fieldName.equals("type")) {

			if (fieldValue
					.equals(EpicamEnumConstants.SMSPREDEFINI_TYPE_SENSIBILISATION))
				value = rb
						.getString("smsPredefini_type_sensibilisation_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.SMSPREDEFINI_TYPE_QUIZZ))
				value = rb.getString("smsPredefini_type_quizz_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.SMSPREDEFINI_TYPE_RAPPELRDV))
				value = rb.getString("smsPredefini_type_rappelRDV_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.SMSPREDEFINI_TYPE_MEDICALRECORD))
				value = rb.getString("smsPredefini_type_medicalRecord_option");

		}

		if (fieldName.equals("periodicite")) {

			if (fieldValue
					.equals(EpicamEnumConstants.SMSPREDEFINI_PERIODICITE_JOUR))
				value = rb.getString("smsPredefini_periodicite_jour_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.SMSPREDEFINI_PERIODICITE_SEMAINE))
				value = rb.getString("smsPredefini_periodicite_semaine_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.SMSPREDEFINI_PERIODICITE_MOIS))
				value = rb.getString("smsPredefini_periodicite_mois_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.SMSPREDEFINI_PERIODICITE_TRIMESTRE))
				value = rb
						.getString("smsPredefini_periodicite_trimestre_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.SMSPREDEFINI_PERIODICITE_SEMESTRE))
				value = rb
						.getString("smsPredefini_periodicite_semestre_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.SMSPREDEFINI_PERIODICITE_PONCTUELLE))
				value = rb
						.getString("smsPredefini_periodicite_ponctuelle_option");

		}

		if (fieldName.equals("statut")) {

			if (fieldValue
					.equals(EpicamEnumConstants.SMSPREDEFINI_STATUT_ACTIF))
				value = rb.getString("smsPredefini_statut_actif_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.SMSPREDEFINI_STATUT_INACTIF))
				value = rb.getString("smsPredefini_statut_inactif_option");

		}

		return value.trim();
	}

	/**
	 *
	 */
	public String getOutBoxEnumDisplayValue(String fieldName, String fieldValue) {
		String value = "";

		if (fieldName.equals("statut")) {

			if (fieldValue.equals(EpicamEnumConstants.OUTBOX_STATUT_ERREUR))
				value = rb.getString("outBox_statut_erreur_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.OUTBOX_STATUT_AENVOYER))
				value = rb.getString("outBox_statut_aEnvoyer_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.OUTBOX_STATUT_SUCCES))
				value = rb.getString("outBox_statut_succes_option");

		}

		return value.trim();
	}

	/**
	 *
	 */
	public String getUtilisateurEnumDisplayValue(String fieldName,
			String fieldValue) {
		String value = "";

		if (fieldName.equals("libelle")) {

			if (fieldValue
					.equals(EpicamEnumConstants.UTILISATEUR_LIBELLE_DOMICILE))
				value = rb.getString("utilisateur_libelle_domicile_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.UTILISATEUR_LIBELLE_BUREAU))
				value = rb.getString("utilisateur_libelle_bureau_option");
			else if (fieldValue
					.equals(EpicamEnumConstants.UTILISATEUR_LIBELLE_AUTRE))
				value = rb.getString("utilisateur_libelle_autre_option");

		}

		return value.trim();
	}

	/**
	 * Gets the text corresponding to a given locale
	 * @param localizedText the localizedText for which the text has to be returned
	 * @return the text corresponding to a given locale (if empty, returns the first non empty text)
	 */
	public String getLocalizedText(LocalizedText localizedText) {

		String locale = rb.getString("locale");

		if (localizedText != null) {

			String text = "";

			if (locale.equals("fr"))
				text = localizedText.getFrancais();

			if (locale.equals("en"))
				text = localizedText.getEnglish();

			if (text != null && !text.isEmpty())
				return text;
			else { // return first not empty text

				if (localizedText.getFrancais() != null
						&& !localizedText.getFrancais().isEmpty())
					return localizedText.getFrancais();

				if (localizedText.getEnglish() != null
						&& !localizedText.getEnglish().isEmpty())
					return localizedText.getEnglish();

			}
		}
		return "";
	}

	public static String getBooleanDisplayValue(Boolean bool) {
		if (bool != null) {
			if (bool.booleanValue())
				return rb.getString("boolean_true");
			else
				return rb.getString("boolean_false");
		} else
			return "";
	}

	public static String getDateDisplayValue(Date date) {
		if (date != null)
			return DateUtil.getFormatedDate(date);
		else
			return "";
	}

	public String getEntityTypeDisplayValue(ImogBean bean) {

		if (bean instanceof Patient) {
			return rb.getString("patient_name");
		}
		if (bean instanceof CasIndex) {
			return rb.getString("casIndex_name");
		}
		if (bean instanceof CasTuberculose) {
			return rb.getString("casTuberculose_name");
		}
		if (bean instanceof ExamenSerologie) {
			return rb.getString("examenSerologie_name");
		}
		if (bean instanceof ExamenBiologique) {
			return rb.getString("examenBiologique_name");
		}
		if (bean instanceof ExamenMicroscopie) {
			return rb.getString("examenMicroscopie_name");
		}
		if (bean instanceof ExamenATB) {
			return rb.getString("examenATB_name");
		}
		if (bean instanceof PriseMedicamenteuse) {
			return rb.getString("priseMedicamenteuse_name");
		}
		if (bean instanceof RendezVous) {
			return rb.getString("rendezVous_name");
		}
		if (bean instanceof TransfertReference) {
			return rb.getString("transfertReference_name");
		}
		if (bean instanceof Lot) {
			return rb.getString("lot_name");
		}
		if (bean instanceof HorsUsage) {
			return rb.getString("horsUsage_name");
		}
		if (bean instanceof EntreeLot) {
			return rb.getString("entreeLot_name");
		}
		if (bean instanceof SortieLot) {
			return rb.getString("sortieLot_name");
		}
		if (bean instanceof Commande) {
			return rb.getString("commande_name");
		}
		if (bean instanceof DetailCommandeMedicament) {
			return rb.getString("detailCommandeMedicament_name");
		}
		if (bean instanceof DetailCommandeIntrant) {
			return rb.getString("detailCommandeIntrant_name");
		}
		if (bean instanceof Reception) {
			return rb.getString("reception_name");
		}
		if (bean instanceof DetailReceptionMedicament) {
			return rb.getString("detailReceptionMedicament_name");
		}
		if (bean instanceof DetailReceptionIntrant) {
			return rb.getString("detailReceptionIntrant_name");
		}
		if (bean instanceof Ravitaillement) {
			return rb.getString("ravitaillement_name");
		}
		if (bean instanceof DetailRavitaillement) {
			return rb.getString("detailRavitaillement_name");
		}
		if (bean instanceof Inventaire) {
			return rb.getString("inventaire_name");
		}
		if (bean instanceof DetailInventaire) {
			return rb.getString("detailInventaire_name");
		}
		if (bean instanceof Personnel) {
			return rb.getString("personnel_name");
		}
		if (bean instanceof DepartPersonnel) {
			return rb.getString("departPersonnel_name");
		}
		if (bean instanceof ArriveePersonnel) {
			return rb.getString("arriveePersonnel_name");
		}
		if (bean instanceof Region) {
			return rb.getString("region_name");
		}
		if (bean instanceof DistrictSante) {
			return rb.getString("districtSante_name");
		}
		if (bean instanceof CentreDiagTrait) {
			return rb.getString("centreDiagTrait_name");
		}
		if (bean instanceof LaboratoireReference) {
			return rb.getString("laboratoireReference_name");
		}
		if (bean instanceof LieuDit) {
			return rb.getString("lieuDit_name");
		}
		if (bean instanceof Regime) {
			return rb.getString("regime_name");
		}
		if (bean instanceof PriseMedicamentRegime) {
			return rb.getString("priseMedicamentRegime_name");
		}
		if (bean instanceof Medicament) {
			return rb.getString("medicament_name");
		}
		if (bean instanceof Intrant) {
			return rb.getString("intrant_name");
		}
		if (bean instanceof Formation) {
			return rb.getString("formation_name");
		}
		if (bean instanceof CandidatureFormation) {
			return rb.getString("candidatureFormation_name");
		}
		if (bean instanceof Qualification) {
			return rb.getString("qualification_name");
		}
		if (bean instanceof Tutoriel) {
			return rb.getString("tutoriel_name");
		}
		if (bean instanceof SmsPredefini) {
			return rb.getString("smsPredefini_name");
		}
		if (bean instanceof OutBox) {
			return rb.getString("outBox_name");
		}
		if (bean instanceof Utilisateur) {
			return rb.getString("utilisateur_name");
		}
		return "";
	}

}
