package org.imogene.epicam.shared ; @ExtraTypes({LocalizedTextProxy.class, PersonnelProxy.class, UtilisateurProxy.class})public interface EpicamRequestFactory extends AdminRequestFactory { PatientRequest patientRequest() ;  CasIndexRequest casIndexRequest() ;  CasTuberculoseRequest casTuberculoseRequest() ;  ExamenSerologieRequest examenSerologieRequest() ;  ExamenBiologiqueRequest examenBiologiqueRequest() ;  ExamenMicroscopieRequest examenMicroscopieRequest() ;  ExamenATBRequest examenATBRequest() ;  PriseMedicamenteuseRequest priseMedicamenteuseRequest() ;  RendezVousRequest rendezVousRequest() ;  TransfertReferenceRequest transfertReferenceRequest() ;  LotRequest lotRequest() ;  HorsUsageRequest horsUsageRequest() ;  EntreeLotRequest entreeLotRequest() ;  SortieLotRequest sortieLotRequest() ;  CommandeRequest commandeRequest() ;  DetailCommandeMedicamentRequest detailCommandeMedicamentRequest() ;  DetailCommandeIntrantRequest detailCommandeIntrantRequest() ;  ReceptionRequest receptionRequest() ;  DetailReceptionMedicamentRequest detailReceptionMedicamentRequest() ;  DetailReceptionIntrantRequest detailReceptionIntrantRequest() ;  RavitaillementRequest ravitaillementRequest() ;  DetailRavitaillementRequest detailRavitaillementRequest() ;  InventaireRequest inventaireRequest() ;  DetailInventaireRequest detailInventaireRequest() ;  PersonnelRequest personnelRequest() ;  DepartPersonnelRequest departPersonnelRequest() ;  ArriveePersonnelRequest arriveePersonnelRequest() ;  RegionRequest regionRequest() ;  DistrictSanteRequest districtSanteRequest() ;  CentreDiagTraitRequest centreDiagTraitRequest() ;  LaboratoireReferenceRequest laboratoireReferenceRequest() ;  LieuDitRequest lieuDitRequest() ;  RegimeRequest regimeRequest() ;  PriseMedicamentRegimeRequest priseMedicamentRegimeRequest() ;  MedicamentRequest medicamentRequest() ;  IntrantRequest intrantRequest() ;  FormationRequest formationRequest() ;  CandidatureFormationRequest candidatureFormationRequest() ;  QualificationRequest qualificationRequest() ;  TutorielRequest tutorielRequest() ;  SmsPredefiniRequest smsPredefiniRequest() ;  OutBoxRequest outBoxRequest() ;  UtilisateurRequest utilisateurRequest() ; }