package org.imogene.epicam.domain.entity.backup ; public class CloneFactoryImpl implements CloneFactory { private static CloneFactory instance = null ;  public static CloneFactory getInstance() { if (instance == null) instance = new CloneFactoryImpl() ;  return instance ;  }  public Object clone(Object source) { if (source instanceof Patient) return cloneEntity((Patient) source) ;  if (source instanceof CasIndex) return cloneEntity((CasIndex) source) ;  if (source instanceof CasTuberculose) return cloneEntity((CasTuberculose) source) ;  if (source instanceof ExamenSerologie) return cloneEntity((ExamenSerologie) source) ;  if (source instanceof ExamenBiologique) return cloneEntity((ExamenBiologique) source) ;  if (source instanceof ExamenMicroscopie) return cloneEntity((ExamenMicroscopie) source) ;  if (source instanceof ExamenATB) return cloneEntity((ExamenATB) source) ;  if (source instanceof PriseMedicamenteuse) return cloneEntity((PriseMedicamenteuse) source) ;  if (source instanceof RendezVous) return cloneEntity((RendezVous) source) ;  if (source instanceof TransfertReference) return cloneEntity((TransfertReference) source) ;  if (source instanceof Lot) return cloneEntity((Lot) source) ;  if (source instanceof HorsUsage) return cloneEntity((HorsUsage) source) ;  if (source instanceof EntreeLot) return cloneEntity((EntreeLot) source) ;  if (source instanceof SortieLot) return cloneEntity((SortieLot) source) ;  if (source instanceof Commande) return cloneEntity((Commande) source) ;  if (source instanceof DetailCommandeMedicament) return cloneEntity((DetailCommandeMedicament) source) ;  if (source instanceof DetailCommandeIntrant) return cloneEntity((DetailCommandeIntrant) source) ;  if (source instanceof Reception) return cloneEntity((Reception) source) ;  if (source instanceof DetailReceptionMedicament) return cloneEntity((DetailReceptionMedicament) source) ;  if (source instanceof DetailReceptionIntrant) return cloneEntity((DetailReceptionIntrant) source) ;  if (source instanceof Ravitaillement) return cloneEntity((Ravitaillement) source) ;  if (source instanceof DetailRavitaillement) return cloneEntity((DetailRavitaillement) source) ;  if (source instanceof Inventaire) return cloneEntity((Inventaire) source) ;  if (source instanceof DetailInventaire) return cloneEntity((DetailInventaire) source) ;  if (source instanceof Personnel) return cloneEntity((Personnel) source) ;  if (source instanceof DepartPersonnel) return cloneEntity((DepartPersonnel) source) ;  if (source instanceof ArriveePersonnel) return cloneEntity((ArriveePersonnel) source) ;  if (source instanceof Region) return cloneEntity((Region) source) ;  if (source instanceof DistrictSante) return cloneEntity((DistrictSante) source) ;  if (source instanceof CentreDiagTrait) return cloneEntity((CentreDiagTrait) source) ;  if (source instanceof LaboratoireReference) return cloneEntity((LaboratoireReference) source) ;  if (source instanceof LieuDit) return cloneEntity((LieuDit) source) ;  if (source instanceof Regime) return cloneEntity((Regime) source) ;  if (source instanceof PriseMedicamentRegime) return cloneEntity((PriseMedicamentRegime) source) ;  if (source instanceof Medicament) return cloneEntity((Medicament) source) ;  if (source instanceof Intrant) return cloneEntity((Intrant) source) ;  if (source instanceof Formation) return cloneEntity((Formation) source) ;  if (source instanceof CandidatureFormation) return cloneEntity((CandidatureFormation) source) ;  if (source instanceof Qualification) return cloneEntity((Qualification) source) ;  if (source instanceof Tutoriel) return cloneEntity((Tutoriel) source) ;  if (source instanceof SmsPredefini) return cloneEntity((SmsPredefini) source) ;  if (source instanceof OutBox) return cloneEntity((OutBox) source) ;  if (source instanceof Utilisateur) return cloneEntity((Utilisateur) source) ;  return null ;  }  public PatientBck cloneEntity(Patient entity) { return PatientCloner.cloneEntity(entity) ;  }  public CasIndexBck cloneEntity(CasIndex entity) { return CasIndexCloner.cloneEntity(entity) ;  }  public CasTuberculoseBck cloneEntity(CasTuberculose entity) { return CasTuberculoseCloner.cloneEntity(entity) ;  }  public ExamenSerologieBck cloneEntity(ExamenSerologie entity) { return ExamenSerologieCloner.cloneEntity(entity) ;  }  public ExamenBiologiqueBck cloneEntity(ExamenBiologique entity) { return ExamenBiologiqueCloner.cloneEntity(entity) ;  }  public ExamenMicroscopieBck cloneEntity(ExamenMicroscopie entity) { return ExamenMicroscopieCloner.cloneEntity(entity) ;  }  public ExamenATBBck cloneEntity(ExamenATB entity) { return ExamenATBCloner.cloneEntity(entity) ;  }  public PriseMedicamenteuseBck cloneEntity(PriseMedicamenteuse entity) { return PriseMedicamenteuseCloner.cloneEntity(entity) ;  }  public RendezVousBck cloneEntity(RendezVous entity) { return RendezVousCloner.cloneEntity(entity) ;  }  public TransfertReferenceBck cloneEntity(TransfertReference entity) { return TransfertReferenceCloner.cloneEntity(entity) ;  }  public LotBck cloneEntity(Lot entity) { return LotCloner.cloneEntity(entity) ;  }  public HorsUsageBck cloneEntity(HorsUsage entity) { return HorsUsageCloner.cloneEntity(entity) ;  }  public EntreeLotBck cloneEntity(EntreeLot entity) { return EntreeLotCloner.cloneEntity(entity) ;  }  public SortieLotBck cloneEntity(SortieLot entity) { return SortieLotCloner.cloneEntity(entity) ;  }  public CommandeBck cloneEntity(Commande entity) { return CommandeCloner.cloneEntity(entity) ;  }  public DetailCommandeMedicamentBck cloneEntity( DetailCommandeMedicament entity) { return DetailCommandeMedicamentCloner.cloneEntity(entity) ;  }  public DetailCommandeIntrantBck cloneEntity(DetailCommandeIntrant entity) { return DetailCommandeIntrantCloner.cloneEntity(entity) ;  }  public ReceptionBck cloneEntity(Reception entity) { return ReceptionCloner.cloneEntity(entity) ;  }  public DetailReceptionMedicamentBck cloneEntity( DetailReceptionMedicament entity) { return DetailReceptionMedicamentCloner.cloneEntity(entity) ;  }  public DetailReceptionIntrantBck cloneEntity(DetailReceptionIntrant entity) { return DetailReceptionIntrantCloner.cloneEntity(entity) ;  }  public RavitaillementBck cloneEntity(Ravitaillement entity) { return RavitaillementCloner.cloneEntity(entity) ;  }  public DetailRavitaillementBck cloneEntity(DetailRavitaillement entity) { return DetailRavitaillementCloner.cloneEntity(entity) ;  }  public InventaireBck cloneEntity(Inventaire entity) { return InventaireCloner.cloneEntity(entity) ;  }  public DetailInventaireBck cloneEntity(DetailInventaire entity) { return DetailInventaireCloner.cloneEntity(entity) ;  }  public PersonnelBck cloneEntity(Personnel entity) { return PersonnelCloner.cloneEntity(entity) ;  }  public DepartPersonnelBck cloneEntity(DepartPersonnel entity) { return DepartPersonnelCloner.cloneEntity(entity) ;  }  public ArriveePersonnelBck cloneEntity(ArriveePersonnel entity) { return ArriveePersonnelCloner.cloneEntity(entity) ;  }  public RegionBck cloneEntity(Region entity) { return RegionCloner.cloneEntity(entity) ;  }  public DistrictSanteBck cloneEntity(DistrictSante entity) { return DistrictSanteCloner.cloneEntity(entity) ;  }  public CentreDiagTraitBck cloneEntity(CentreDiagTrait entity) { return CentreDiagTraitCloner.cloneEntity(entity) ;  }  public LaboratoireReferenceBck cloneEntity(LaboratoireReference entity) { return LaboratoireReferenceCloner.cloneEntity(entity) ;  }  public LieuDitBck cloneEntity(LieuDit entity) { return LieuDitCloner.cloneEntity(entity) ;  }  public RegimeBck cloneEntity(Regime entity) { return RegimeCloner.cloneEntity(entity) ;  }  public PriseMedicamentRegimeBck cloneEntity(PriseMedicamentRegime entity) { return PriseMedicamentRegimeCloner.cloneEntity(entity) ;  }  public MedicamentBck cloneEntity(Medicament entity) { return MedicamentCloner.cloneEntity(entity) ;  }  public IntrantBck cloneEntity(Intrant entity) { return IntrantCloner.cloneEntity(entity) ;  }  public FormationBck cloneEntity(Formation entity) { return FormationCloner.cloneEntity(entity) ;  }  public CandidatureFormationBck cloneEntity(CandidatureFormation entity) { return CandidatureFormationCloner.cloneEntity(entity) ;  }  public QualificationBck cloneEntity(Qualification entity) { return QualificationCloner.cloneEntity(entity) ;  }  public TutorielBck cloneEntity(Tutoriel entity) { return TutorielCloner.cloneEntity(entity) ;  }  public SmsPredefiniBck cloneEntity(SmsPredefini entity) { return SmsPredefiniCloner.cloneEntity(entity) ;  }  public OutBoxBck cloneEntity(OutBox entity) { return OutBoxCloner.cloneEntity(entity) ;  }  public UtilisateurBck cloneEntity(Utilisateur entity) { return UtilisateurCloner.cloneEntity(entity) ;  } }