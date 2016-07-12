/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2008                    */
/* Created on:     12-Jul-16 9:50:12 AM                         */
/*==============================================================*/


if exists (select 1
          from sysobjects
          where id = object_id('CLR_TRIGGER_KORISNIK')
          and type = 'TR')
   drop trigger CLR_TRIGGER_KORISNIK
go

if exists (select 1
          from sysobjects
          where id = object_id('TI_KORISNIK')
          and type = 'TR')
   drop trigger TI_KORISNIK
go

if exists (select 1
          from sysobjects
          where id = object_id('TU_KORISNIK')
          and type = 'TR')
   drop trigger TU_KORISNIK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('ANALITIKA_MAGACINSKE_KARTICE')
            and   name  = 'IMA_FK'
            and   indid > 0
            and   indid < 255)
   drop index ANALITIKA_MAGACINSKE_KARTICE.IMA_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('ANALITIKA_MAGACINSKE_KARTICE')
            and   name  = 'RELATIONSHIP_9_FK'
            and   indid > 0
            and   indid < 255)
   drop index ANALITIKA_MAGACINSKE_KARTICE.RELATIONSHIP_9_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('ANALITIKA_MAGACINSKE_KARTICE')
            and   type = 'U')
   drop table ANALITIKA_MAGACINSKE_KARTICE
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('ARTIKAL')
            and   name  = 'KOLICINA_U_JEDINICI_MERE_FK'
            and   indid > 0
            and   indid < 255)
   drop index ARTIKAL.KOLICINA_U_JEDINICI_MERE_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('ARTIKAL')
            and   type = 'U')
   drop table ARTIKAL
go

if exists (select 1
            from  sysobjects
           where  id = object_id('DRZAVA')
            and   type = 'U')
   drop table DRZAVA
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('KORISNIK')
            and   name  = 'JE_FK'
            and   indid > 0
            and   indid < 255)
   drop index KORISNIK.JE_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('KORISNIK')
            and   type = 'U')
   drop table KORISNIK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('MAGACIN')
            and   name  = 'PRIPADA_FK'
            and   indid > 0
            and   indid < 255)
   drop index MAGACIN.PRIPADA_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('MAGACIN')
            and   type = 'U')
   drop table MAGACIN
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('MAGACINSKA_KARTICA')
            and   name  = 'KARTICA_ZA_MAGACIN_FK'
            and   indid > 0
            and   indid < 255)
   drop index MAGACINSKA_KARTICA.KARTICA_ZA_MAGACIN_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('MAGACINSKA_KARTICA')
            and   name  = 'ZA_GODINU_FK'
            and   indid > 0
            and   indid < 255)
   drop index MAGACINSKA_KARTICA.ZA_GODINU_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('MAGACINSKA_KARTICA')
            and   name  = 'ULAZI_U_KARTICU_FK'
            and   indid > 0
            and   indid < 255)
   drop index MAGACINSKA_KARTICA.ULAZI_U_KARTICU_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('MAGACINSKA_KARTICA')
            and   type = 'U')
   drop table MAGACINSKA_KARTICA
go

if exists (select 1
            from  sysobjects
           where  id = object_id('MERNA_JEDINICA')
            and   type = 'U')
   drop table MERNA_JEDINICA
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('NASELJENO_MESTO')
            and   name  = 'NALAZI_SE_U_FK'
            and   indid > 0
            and   indid < 255)
   drop index NASELJENO_MESTO.NALAZI_SE_U_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('NASELJENO_MESTO')
            and   type = 'U')
   drop table NASELJENO_MESTO
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('POPISNA_KOMISIJA')
            and   name  = 'FORMIRA_DOKUMENT_FK'
            and   indid > 0
            and   indid < 255)
   drop index POPISNA_KOMISIJA.FORMIRA_DOKUMENT_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('POPISNA_KOMISIJA')
            and   name  = 'ZADUZENI_ZA_POPIS_FK'
            and   indid > 0
            and   indid < 255)
   drop index POPISNA_KOMISIJA.ZADUZENI_ZA_POPIS_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('POPISNA_KOMISIJA')
            and   type = 'U')
   drop table POPISNA_KOMISIJA
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('POPISNI_DOKUMENT')
            and   name  = 'KOLICINA_NA_POPISU_FK'
            and   indid > 0
            and   indid < 255)
   drop index POPISNI_DOKUMENT.KOLICINA_NA_POPISU_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('POPISNI_DOKUMENT')
            and   type = 'U')
   drop table POPISNI_DOKUMENT
go

if exists (select 1
            from  sysobjects
           where  id = object_id('POSLOVNA_GODINA')
            and   type = 'U')
   drop table POSLOVNA_GODINA
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('POSLOVNI_PARTNER')
            and   name  = 'IMA_PARTNERA_FK'
            and   indid > 0
            and   indid < 255)
   drop index POSLOVNI_PARTNER.IMA_PARTNERA_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('POSLOVNI_PARTNER')
            and   type = 'U')
   drop table POSLOVNI_PARTNER
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('PREDUZECE')
            and   name  = 'SE_NALAZI_FK'
            and   indid > 0
            and   indid < 255)
   drop index PREDUZECE.SE_NALAZI_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('PREDUZECE')
            and   type = 'U')
   drop table PREDUZECE
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('PROMETNI_DOKUMENT')
            and   name  = 'TEKUCA_GODINA_FK'
            and   indid > 0
            and   indid < 255)
   drop index PROMETNI_DOKUMENT.TEKUCA_GODINA_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('PROMETNI_DOKUMENT')
            and   name  = 'RELATIONSHIP_15_FK'
            and   indid > 0
            and   indid < 255)
   drop index PROMETNI_DOKUMENT.RELATIONSHIP_15_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('PROMETNI_DOKUMENT')
            and   name  = 'ZA_MAGACIN_FK'
            and   indid > 0
            and   indid < 255)
   drop index PROMETNI_DOKUMENT.ZA_MAGACIN_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('PROMETNI_DOKUMENT')
            and   name  = 'RELATIONSHIP_13_FK'
            and   indid > 0
            and   indid < 255)
   drop index PROMETNI_DOKUMENT.RELATIONSHIP_13_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('PROMETNI_DOKUMENT')
            and   type = 'U')
   drop table PROMETNI_DOKUMENT
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('STAVKA_POPISA')
            and   name  = 'POPISANI_ARTIKLI_FK'
            and   indid > 0
            and   indid < 255)
   drop index STAVKA_POPISA.POPISANI_ARTIKLI_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('STAVKA_POPISA')
            and   name  = 'SADRZI_FK'
            and   indid > 0
            and   indid < 255)
   drop index STAVKA_POPISA.SADRZI_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('STAVKA_POPISA')
            and   type = 'U')
   drop table STAVKA_POPISA
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('STAVKA_PROMETNOG_DOKUMENTA')
            and   name  = 'SADRZI_STAVKU_FK'
            and   indid > 0
            and   indid < 255)
   drop index STAVKA_PROMETNOG_DOKUMENTA.SADRZI_STAVKU_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('STAVKA_PROMETNOG_DOKUMENTA')
            and   name  = 'PRIPADA_DOKUMENTU_FK'
            and   indid > 0
            and   indid < 255)
   drop index STAVKA_PROMETNOG_DOKUMENTA.PRIPADA_DOKUMENTU_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('STAVKA_PROMETNOG_DOKUMENTA')
            and   type = 'U')
   drop table STAVKA_PROMETNOG_DOKUMENTA
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('ZAPOSLENI')
            and   name  = 'ZAPOSLEN_U_FK'
            and   indid > 0
            and   indid < 255)
   drop index ZAPOSLENI.ZAPOSLEN_U_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('ZAPOSLENI')
            and   type = 'U')
   drop table ZAPOSLENI
go

/*==============================================================*/
/* Table: ANALITIKA_MAGACINSKE_KARTICE                          */
/*==============================================================*/
create table ANALITIKA_MAGACINSKE_KARTICE (
   PIB                  numeric(13)          not null,
   SIFRA_ARTIKLA        numeric(12)          not null,
   MAG_GODINA           numeric(4)           not null,
   OZNAKA_MAGACINA      int                  not null,
   ID_MAGACINSKE_KARTICE int                  not null,
   REDNI_BROJ_AN_       numeric(5)           not null,
   GODINA               numeric(4)           null,
   BROJ                 numeric(5)           null,
   REDNI_BROJ_STAVKE    numeric(5)           null,
   DATUM_PROMENE        datetime             not null,
   VR__DOK              varchar(12)          not null,
   SIFRA_DOKUMENTA      char(12)             not null,
   KOLICINA             decimal(12,2)        not null,
   CENA                 decimal(14,2)        not null,
   VREDNOST             decimal(14,2)        not null,
   SMER                 varchar(12)          not null,
   constraint PK_ANALITIKA_MAGACINSKE_KARTIC primary key nonclustered (PIB, SIFRA_ARTIKLA, MAG_GODINA, OZNAKA_MAGACINA, ID_MAGACINSKE_KARTICE, REDNI_BROJ_AN_)
)
go

/*==============================================================*/
/* Index: RELATIONSHIP_9_FK                                     */
/*==============================================================*/
create index RELATIONSHIP_9_FK on ANALITIKA_MAGACINSKE_KARTICE (
PIB ASC,
SIFRA_ARTIKLA ASC,
MAG_GODINA ASC,
OZNAKA_MAGACINA ASC,
ID_MAGACINSKE_KARTICE ASC
)
go

/*==============================================================*/
/* Index: IMA_FK                                                */
/*==============================================================*/
create index IMA_FK on ANALITIKA_MAGACINSKE_KARTICE (
GODINA ASC,
BROJ ASC,
REDNI_BROJ_STAVKE ASC
)
go

/*==============================================================*/
/* Table: ARTIKAL                                               */
/*==============================================================*/
create table ARTIKAL (
   SIFRA_ARTIKLA        numeric(12)          not null,
   ID_MERNE_JEDINICE    int                  not null,
   NAZIV_ARTIKLA        varchar(30)          not null,
   VELICINA_PAKOVANJA   int                  not null,
   constraint PK_ARTIKAL primary key nonclustered (SIFRA_ARTIKLA)
)
go

/*==============================================================*/
/* Index: KOLICINA_U_JEDINICI_MERE_FK                           */
/*==============================================================*/
create index KOLICINA_U_JEDINICI_MERE_FK on ARTIKAL (
ID_MERNE_JEDINICE ASC
)
go

/*==============================================================*/
/* Table: DRZAVA                                                */
/*==============================================================*/
create table DRZAVA (
   OZNAKA_DRZAVE        varchar(3)           not null,
   IME_DRZAVE           varchar(30)          not null,
   constraint PK_DRZAVA primary key nonclustered (OZNAKA_DRZAVE)
)
go

/*==============================================================*/
/* Table: KORISNIK                                              */
/*==============================================================*/
create table KORISNIK (
   PIB                  numeric(13)          not null,
   JMBG                 bigint               not null,
   IME_NALOGA           varchar(30)          not null,
   LOZINKA_NALOGA       varchar(16)          not null,
   constraint PK_KORISNIK primary key nonclustered (PIB, JMBG, IME_NALOGA, LOZINKA_NALOGA)
)
go

/*==============================================================*/
/* Index: JE_FK                                                 */
/*==============================================================*/
create index JE_FK on KORISNIK (
PIB ASC,
JMBG ASC
)
go

/*==============================================================*/
/* Table: MAGACIN                                               */
/*==============================================================*/
create table MAGACIN (
   PIB                  numeric(13)          not null,
   OZNAKA_MAGACINA      int                  not null,
   NAZIV_MAGACINA       varchar(30)          not null,
   constraint PK_MAGACIN primary key nonclustered (PIB, OZNAKA_MAGACINA)
)
go

/*==============================================================*/
/* Index: PRIPADA_FK                                            */
/*==============================================================*/
create index PRIPADA_FK on MAGACIN (
PIB ASC
)
go

/*==============================================================*/
/* Table: MAGACINSKA_KARTICA                                    */
/*==============================================================*/
create table MAGACINSKA_KARTICA (
   SIFRA_ARTIKLA        numeric(12)          not null,
   GODINA               numeric(4)           not null,
   PIB                  numeric(13)          not null,
   OZNAKA_MAGACINA      int                  not null,
   ID_MAGACINSKE_KARTICE int                  not null,
   PROSECNA_CENA        decimal(14,2)        null,
   POCETNA_KOLICINA     decimal(12,2)        null,
   POCENTA_VREDNOST_KOLICINE decimal(14,2)        null,
   KOLICINA_ULAZA       decimal(12,2)        null,
   VREDNOST_ULAZA       decimal(14,2)        null,
   KOLICINA_IZLAZA      decimal(12,2)        null,
   VEDNOST_IZLAZA       decimal(14,2)        null,
   constraint PK_MAGACINSKA_KARTICA primary key nonclustered (PIB, SIFRA_ARTIKLA, GODINA, OZNAKA_MAGACINA, ID_MAGACINSKE_KARTICE)
)
go

/*==============================================================*/
/* Index: ULAZI_U_KARTICU_FK                                    */
/*==============================================================*/
create index ULAZI_U_KARTICU_FK on MAGACINSKA_KARTICA (
SIFRA_ARTIKLA ASC
)
go

/*==============================================================*/
/* Index: ZA_GODINU_FK                                          */
/*==============================================================*/
create index ZA_GODINU_FK on MAGACINSKA_KARTICA (
GODINA ASC
)
go

/*==============================================================*/
/* Index: KARTICA_ZA_MAGACIN_FK                                 */
/*==============================================================*/
create index KARTICA_ZA_MAGACIN_FK on MAGACINSKA_KARTICA (
PIB ASC,
OZNAKA_MAGACINA ASC
)
go

/*==============================================================*/
/* Table: MERNA_JEDINICA                                        */
/*==============================================================*/
create table MERNA_JEDINICA (
   ID_MERNE_JEDINICE    int                  not null,
   OZNAKA_MERNE_JEDINICE varchar(3)           not null,
   NAZIV_MERNE_JEDINICE varchar(25)          not null,
   constraint PK_MERNA_JEDINICA primary key nonclustered (ID_MERNE_JEDINICE)
)
go

/*==============================================================*/
/* Table: NASELJENO_MESTO                                       */
/*==============================================================*/
create table NASELJENO_MESTO (
   OZNAKA_DRZAVE        varchar(3)           not null,
   OZNAKA_NASELJENOG_MESTA varchar(3)           not null,
   IME_NASELJENOG_MESTA varchar(30)          not null,
   constraint PK_NASELJENO_MESTO primary key nonclustered (OZNAKA_DRZAVE, OZNAKA_NASELJENOG_MESTA)
)
go

/*==============================================================*/
/* Index: NALAZI_SE_U_FK                                        */
/*==============================================================*/
create index NALAZI_SE_U_FK on NASELJENO_MESTO (
OZNAKA_DRZAVE ASC
)
go

/*==============================================================*/
/* Table: POPISNA_KOMISIJA                                      */
/*==============================================================*/
create table POPISNA_KOMISIJA (
   PIB                  numeric(13)          not null,
   JMBG                 bigint               not null,
   GODINA               numeric(4)           not null,
   REDNI_BROJ_POPISA    numeric(4)           not null,
   FUNCKIJA             varchar(15)          null,
   constraint PK_POPISNA_KOMISIJA primary key (PIB, GODINA, JMBG, REDNI_BROJ_POPISA)
)
go

/*==============================================================*/
/* Index: ZADUZENI_ZA_POPIS_FK                                  */
/*==============================================================*/
create index ZADUZENI_ZA_POPIS_FK on POPISNA_KOMISIJA (
PIB ASC,
JMBG ASC
)
go

/*==============================================================*/
/* Index: FORMIRA_DOKUMENT_FK                                   */
/*==============================================================*/
create index FORMIRA_DOKUMENT_FK on POPISNA_KOMISIJA (
GODINA ASC,
REDNI_BROJ_POPISA ASC
)
go

/*==============================================================*/
/* Table: POPISNI_DOKUMENT                                      */
/*==============================================================*/
create table POPISNI_DOKUMENT (
   GODINA               numeric(4)           not null,
   REDNI_BROJ_POPISA    numeric(4)           not null,
   DATUM_POPISA         datetime             null,
   STATUS               bit                  null,
   constraint PK_POPISNI_DOKUMENT primary key nonclustered (GODINA, REDNI_BROJ_POPISA)
)
go

/*==============================================================*/
/* Index: KOLICINA_NA_POPISU_FK                                 */
/*==============================================================*/
create index KOLICINA_NA_POPISU_FK on POPISNI_DOKUMENT (
GODINA ASC
)
go

/*==============================================================*/
/* Table: POSLOVNA_GODINA                                       */
/*==============================================================*/
create table POSLOVNA_GODINA (
   GODINA               numeric(4)           not null,
   AKTIVNA              bit                  null,
   constraint PK_POSLOVNA_GODINA primary key nonclustered (GODINA)
)
go

/*==============================================================*/
/* Table: POSLOVNI_PARTNER                                      */
/*==============================================================*/
create table POSLOVNI_PARTNER (
   PIB                  numeric(13)          not null,
   PIB_PARTNERA         numeric(13)          not null,
   constraint PK_POSLOVNI_PARTNER primary key nonclustered (PIB, PIB_PARTNERA)
)
go

/*==============================================================*/
/* Index: IMA_PARTNERA_FK                                       */
/*==============================================================*/
create index IMA_PARTNERA_FK on POSLOVNI_PARTNER (
PIB ASC
)
go

/*==============================================================*/
/* Table: PREDUZECE                                             */
/*==============================================================*/
create table PREDUZECE (
   PIB                  numeric(13)          not null,
   OZNAKA_DRZAVE        varchar(3)           not null,
   OZNAKA_NASELJENOG_MESTA varchar(3)           not null,
   NAZIV_PREDUZECA      varchar(30)          not null,
   ADRESA               varchar(30)          null,
   TELEFON              numeric(20)          null,
   constraint PK_PREDUZECE primary key nonclustered (PIB)
)
go

/*==============================================================*/
/* Index: SE_NALAZI_FK                                          */
/*==============================================================*/
create index SE_NALAZI_FK on PREDUZECE (
OZNAKA_DRZAVE ASC,
OZNAKA_NASELJENOG_MESTA ASC
)
go

/*==============================================================*/
/* Table: PROMETNI_DOKUMENT                                     */
/*==============================================================*/
create table PROMETNI_DOKUMENT (
   GODINA               numeric(4)           not null,
   BROJ                 numeric(5)           not null,
   POS_PIB              numeric(13)          null,
   PIB_PARTNERA         numeric(13)          null,
   PIB                  numeric(13)          null,
   OZNAKA_MAGACINA      int                  null,
   MAG_PIB              numeric(13)          not null,
   MAG_OZNAKA_MAGACINA  int                  not null,
   DATUM_NASTANKA       datetime             null,
   DATUM_KNJIZENJA      datetime             null,
   STATUS               bit                  null,
   constraint PK_PROMETNI_DOKUMENT primary key nonclustered (GODINA, BROJ)
)
go

/*==============================================================*/
/* Index: RELATIONSHIP_13_FK                                    */
/*==============================================================*/
create index RELATIONSHIP_13_FK on PROMETNI_DOKUMENT (
PIB ASC,
OZNAKA_MAGACINA ASC
)
go

/*==============================================================*/
/* Index: ZA_MAGACIN_FK                                         */
/*==============================================================*/
create index ZA_MAGACIN_FK on PROMETNI_DOKUMENT (
MAG_PIB ASC,
MAG_OZNAKA_MAGACINA ASC
)
go

/*==============================================================*/
/* Index: RELATIONSHIP_15_FK                                    */
/*==============================================================*/
create index RELATIONSHIP_15_FK on PROMETNI_DOKUMENT (
POS_PIB ASC,
PIB_PARTNERA ASC
)
go

/*==============================================================*/
/* Index: TEKUCA_GODINA_FK                                      */
/*==============================================================*/
create index TEKUCA_GODINA_FK on PROMETNI_DOKUMENT (
GODINA ASC
)
go

/*==============================================================*/
/* Table: STAVKA_POPISA                                         */
/*==============================================================*/
create table STAVKA_POPISA (
   GODINA               numeric(4)           not null,
   REDNI_BROJ_POPISA    numeric(4)           not null,
   SIFRA_ARTIKLA        numeric(12)          not null,
   KOLICINA_PO_POPISU   decimal(12,2)        not null,
   VREDNOST_PO_POPISU   decimal(12,2)        null,
   CENA_PO_POPISU       decimal(12,2)        not null,
   KOLICINA_U_KARTICI   decimal(12,2)        not null,
   CENA_U_KARTICI       decimal(12,2)        not null,
   VREDNOST_U_KARTICI   decimal(12,2)        null,
   constraint PK_STAVKA_POPISA primary key (GODINA, REDNI_BROJ_POPISA, SIFRA_ARTIKLA)
)
go

/*==============================================================*/
/* Index: SADRZI_FK                                             */
/*==============================================================*/
create index SADRZI_FK on STAVKA_POPISA (
GODINA ASC,
REDNI_BROJ_POPISA ASC
)
go

/*==============================================================*/
/* Index: POPISANI_ARTIKLI_FK                                   */
/*==============================================================*/
create index POPISANI_ARTIKLI_FK on STAVKA_POPISA (
SIFRA_ARTIKLA ASC
)
go

/*==============================================================*/
/* Table: STAVKA_PROMETNOG_DOKUMENTA                            */
/*==============================================================*/
create table STAVKA_PROMETNOG_DOKUMENTA (
   GODINA               numeric(4)           not null,
   BROJ                 numeric(5)           not null,
   REDNI_BROJ_STAVKE    numeric(5)           not null,
   SIFRA_ARTIKLA        numeric(12)          not null,
   KOLICINA             decimal(12,2)        not null,
   CENA                 decimal(14,2)        not null,
   VREDNOST             decimal(14,2)        null,
   constraint PK_STAVKA_PROMETNOG_DOKUMENTA primary key nonclustered (GODINA, BROJ, REDNI_BROJ_STAVKE)
)
go

/*==============================================================*/
/* Index: PRIPADA_DOKUMENTU_FK                                  */
/*==============================================================*/
create index PRIPADA_DOKUMENTU_FK on STAVKA_PROMETNOG_DOKUMENTA (
SIFRA_ARTIKLA ASC
)
go

/*==============================================================*/
/* Index: SADRZI_STAVKU_FK                                      */
/*==============================================================*/
create index SADRZI_STAVKU_FK on STAVKA_PROMETNOG_DOKUMENTA (
GODINA ASC,
BROJ ASC
)
go

/*==============================================================*/
/* Table: ZAPOSLENI                                             */
/*==============================================================*/
create table ZAPOSLENI (
   PIB                  numeric(13)          not null,
   JMBG                 bigint               not null,
   IME                  varchar(30)          not null,
   PREZIME              varchar(30)          not null,
   ADRESA               varchar(30)          null,
   constraint PK_ZAPOSLENI primary key nonclustered (PIB, JMBG)
)
go

/*==============================================================*/
/* Index: ZAPOSLEN_U_FK                                         */
/*==============================================================*/
create index ZAPOSLEN_U_FK on ZAPOSLENI (
PIB ASC
)
go

