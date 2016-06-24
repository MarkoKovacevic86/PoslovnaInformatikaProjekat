/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     6/12/2016 4:56:50 PM                         */
/*==============================================================*/


drop table if exists ANALITIKA_MAGACINSKE_KARTICE;

drop table if exists ARTIKAL;

drop table if exists DRZAVA;

drop table if exists KORISNIK;

drop table if exists MAGACIN;

drop table if exists MAGACINSKA_KARTICA;

drop table if exists MERNA_JEDINICA;

drop table if exists NASELJENO_MESTO;

drop table if exists POPISNA_KOMISIJA;

drop table if exists POPISNI_DOKUMENT;

drop table if exists POSLOVNA_GODINA;

drop table if exists POSLOVNI_PARTNER;

drop table if exists PREDUZECE;

drop table if exists PROMETNI_DOKUMENT;

drop table if exists STAVKA_POPISA;

drop table if exists STAVKA_PROMETNOG_DOKUMENTA;

drop table if exists TIP_KORISNIKA;

drop table if exists ZAPOSLENI;

/*==============================================================*/
/* Table: ANALITIKA_MAGACINSKE_KARTICE                          */
/*==============================================================*/
create table ANALITIKA_MAGACINSKE_KARTICE
(
   PIB                  bigint not null,
   SIFRA_ARTIKLA        int not null,
   MAG_GODINA           char(4) not null,
   OZNAKA_MAGACINA      int not null,
   REDNI_BROJ_AN_       numeric(5,0) not null,
   GODINA               char(4),
   BROJ                 national char(5),
   REDNI_BROJ_STAVKE    numeric(5,0),
   DATUM_PROMENE        date not null,
   VR__DOK              varchar(12) not null,
   SIFRA_DOKUMENTA      char(12) not null,
   KOLICINA             decimal(12,2) not null,
   CENA                 decimal(14,2) not null,
   VREDNOST             decimal(14,2) not null,
   SMER                 varchar(12) not null,
   primary key (PIB, SIFRA_ARTIKLA, MAG_GODINA, OZNAKA_MAGACINA, REDNI_BROJ_AN_)
);

/*==============================================================*/
/* Table: ARTIKAL                                               */
/*==============================================================*/
create table ARTIKAL
(
   SIFRA_ARTIKLA        int not null,
   ID_MERNE_JEDINICE    int not null,
   NAZIV_ARTIKLA        varchar(30) not null,
   VELICINA_PAKOVANJA   int not null,
   primary key (SIFRA_ARTIKLA)
);

/*==============================================================*/
/* Table: DRZAVA                                                */
/*==============================================================*/
create table DRZAVA
(
   OZNAKA_DRZAVE        int not null,
   IME_DRZAVE           varchar(30) not null,
   primary key (OZNAKA_DRZAVE)
);

/*==============================================================*/
/* Table: KORISNIK                                              */
/*==============================================================*/
create table KORISNIK
(
   PIB                  bigint not null,
   JMBG                 bigint not null,
   IME_NALOGA           varchar(30) not null,
   LOZINKA_NALOGA       varchar(30) not null,
   primary key (PIB, JMBG, IME_NALOGA, LOZINKA_NALOGA)
);

/*==============================================================*/
/* Table: MAGACIN                                               */
/*==============================================================*/
create table MAGACIN
(
   PIB                  bigint not null,
   OZNAKA_MAGACINA      int not null,
   NAZIV_MAGACINA       varchar(30) not null,
   primary key (PIB, OZNAKA_MAGACINA)
);

/*==============================================================*/
/* Table: MAGACINSKA_KARTICA                                    */
/*==============================================================*/
create table MAGACINSKA_KARTICA
(
   SIFRA_ARTIKLA        int not null,
   GODINA               char(4) not null,
   PIB                  bigint not null,
   OZNAKA_MAGACINA      int not null,
   PROSECNA_CENA        decimal(14,2),
   POCETNA_KOLICINA     decimal(12,2),
   POCENTA_VREDNOST_KOLICINE decimal(14,2),
   KOLICINA_ULAZA       decimal(12,2),
   VREDNOST_ULAZA       decimal(14,2),
   KOLICINA_IZLAZA      decimal(12,2),
   VREDNOST_IZLAZA      decimal(14,2),
   primary key (PIB, SIFRA_ARTIKLA, GODINA, OZNAKA_MAGACINA)
);

/*==============================================================*/
/* Table: MERNA_JEDINICA                                        */
/*==============================================================*/
create table MERNA_JEDINICA
(
   ID_MERNE_JEDINICE    int not null,
   OZNAKA_MERNE_JEDINICE varchar(10) not null,
   NAZIV_MERNE_JEDINICE varchar(25) not null,
   primary key (ID_MERNE_JEDINICE)
);

/*==============================================================*/
/* Table: NASELJENO_MESTO                                       */
/*==============================================================*/
create table NASELJENO_MESTO
(
   OZNAKA_DRZAVE        int not null,
   OZNAKA_NASELJENOG_MESTA int not null,
   IME_NASELJENOG_MESTA varchar(30) not null,
   primary key (OZNAKA_DRZAVE, OZNAKA_NASELJENOG_MESTA)
);

/*==============================================================*/
/* Table: POPISNA_KOMISIJA                                      */
/*==============================================================*/
create table POPISNA_KOMISIJA
(
   PIB                  bigint not null,
   JMBG                 bigint not null,
   GODINA               char(4) not null,
   REDNI_BROJ_POPISA    numeric(4,0) not null,
   FUNKICIJA            varchar(15),
   primary key (PIB, GODINA, JMBG, REDNI_BROJ_POPISA)
);

/*==============================================================*/
/* Table: POPISNI_DOKUMENT                                      */
/*==============================================================*/
create table POPISNI_DOKUMENT
(
   GODINA               char(4) not null,
   REDNI_BROJ_POPISA    numeric(4,0) not null,
   DATUM_POPISA         date,
   STATUS               bool,
   primary key (GODINA, REDNI_BROJ_POPISA)
);

/*==============================================================*/
/* Table: POSLOVNA_GODINA                                       */
/*==============================================================*/
create table POSLOVNA_GODINA
(
   GODINA               char(4) not null,
   AKTIVNA              bool,
   primary key (GODINA)
);

/*==============================================================*/
/* Table: POSLOVNI_PARTNER                                      */
/*==============================================================*/
create table POSLOVNI_PARTNER
(
   PIB                  bigint not null,
   PIB_PARTNERA         bigint not null,
   primary key (PIB, PIB_PARTNERA)
);

/*==============================================================*/
/* Table: PREDUZECE                                             */
/*==============================================================*/
create table PREDUZECE
(
   PIB                  bigint not null,
   OZNAKA_DRZAVE        int not null,
   OZNAKA_NASELJENOG_MESTA int not null,
   NAZIV_PREDUZECA      varchar(30) not null,
   ADRESA               varchar(30),
   TELEFON              varchar(30),
   primary key (PIB)
);

/*==============================================================*/
/* Table: PROMETNI_DOKUMENT                                     */
/*==============================================================*/
create table PROMETNI_DOKUMENT
(
   GODINA               char(4) not null,
   BROJ                 national char(5) not null,
   POS_PIB              bigint,
   PIB_PARTNERA         bigint,
   PIB                  bigint,
   OZNAKA_MAGACINA      int,
   MAG_PIB              bigint not null,
   MAG_OZNAKA_MAGACINA  int not null,
   DATUM_NASTANKA       date,
   DATUM_KNJIZENJA      date,
   STATUS               bool,
   primary key (GODINA, BROJ)
);

/*==============================================================*/
/* Table: STAVKA_POPISA                                         */
/*==============================================================*/
create table STAVKA_POPISA
(
   GODINA               char(4) not null,
   REDNI_BROJ_POPISA    numeric(4,0) not null,
   SIFRA_ARTIKLA        int not null,
   KOLICINA_PO_POPISU   decimal(12,2) not null,
   VREDNOST_PO_POPISU   decimal(12,2),
   CENA_PO_POPISU       decimal(12,2) not null,
   KOLICINA_U_KARTICI   decimal(12,2) not null,
   CENA_U_KARTICI       decimal(12,2) not null,
   VREDNOST_U_KARTICI   decimal(12,2),
   primary key (GODINA, REDNI_BROJ_POPISA, SIFRA_ARTIKLA)
);

/*==============================================================*/
/* Table: STAVKA_PROMETNOG_DOKUMENTA                            */
/*==============================================================*/
create table STAVKA_PROMETNOG_DOKUMENTA
(
   GODINA               char(4) not null,
   BROJ                 national char(5) not null,
   REDNI_BROJ_STAVKE    numeric(5,0) not null,
   SIFRA_ARTIKLA        int not null,
   KOLICINA             decimal(12,2) not null,
   CENA                 decimal(14,2) not null,
   VREDNOST             decimal(14,2),
   primary key (GODINA, BROJ, REDNI_BROJ_STAVKE)
);

/*==============================================================*/
/* Table: TIP_KORISNIKA                                         */
/*==============================================================*/
create table TIP_KORISNIKA
(
   OZNAKA_TIPA          numeric(1,0),
   NAZIV_TIPA           varchar(10)
);

/*==============================================================*/
/* Table: ZAPOSLENI                                             */
/*==============================================================*/
create table ZAPOSLENI
(
   PIB                  bigint not null,
   JMBG                 bigint not null,
   IME                  varchar(30) not null,
   PREZIME              varchar(30) not null,
   ADRESA               varchar(30),
   primary key (PIB, JMBG)
);

alter table ANALITIKA_MAGACINSKE_KARTICE add constraint FK_RELATIONSHIP_10 foreign key (GODINA, BROJ, REDNI_BROJ_STAVKE)
      references STAVKA_PROMETNOG_DOKUMENTA (GODINA, BROJ, REDNI_BROJ_STAVKE) on delete restrict on update restrict;

alter table ANALITIKA_MAGACINSKE_KARTICE add constraint FK_RELATIONSHIP_9 foreign key (PIB, SIFRA_ARTIKLA, MAG_GODINA, OZNAKA_MAGACINA)
      references MAGACINSKA_KARTICA (PIB, SIFRA_ARTIKLA, GODINA, OZNAKA_MAGACINA) on delete restrict on update restrict;

alter table ARTIKAL add constraint FK_RELATIONSHIP_5 foreign key (ID_MERNE_JEDINICE)
      references MERNA_JEDINICA (ID_MERNE_JEDINICE) on delete restrict on update restrict;

alter table KORISNIK add constraint FK_RELATIONSHIP_18 foreign key (PIB, JMBG)
      references ZAPOSLENI (PIB, JMBG) on delete restrict on update restrict;

alter table MAGACIN add constraint FK_RELATIONSHIP_3 foreign key (PIB)
      references PREDUZECE (PIB) on delete restrict on update restrict;

alter table MAGACINSKA_KARTICA add constraint FK_RELATIONSHIP_6 foreign key (SIFRA_ARTIKLA)
      references ARTIKAL (SIFRA_ARTIKLA) on delete restrict on update restrict;

alter table MAGACINSKA_KARTICA add constraint FK_RELATIONSHIP_7 foreign key (GODINA)
      references POSLOVNA_GODINA (GODINA) on delete restrict on update restrict;

alter table MAGACINSKA_KARTICA add constraint FK_RELATIONSHIP_8 foreign key (PIB, OZNAKA_MAGACINA)
      references MAGACIN (PIB, OZNAKA_MAGACINA) on delete restrict on update restrict;

alter table NASELJENO_MESTO add constraint FK_RELATIONSHIP_1 foreign key (OZNAKA_DRZAVE)
      references DRZAVA (OZNAKA_DRZAVE) on delete restrict on update restrict;

alter table POPISNA_KOMISIJA add constraint FK_RELATIONSHIP_23 foreign key (PIB, JMBG)
      references ZAPOSLENI (PIB, JMBG) on delete restrict on update restrict;

alter table POPISNA_KOMISIJA add constraint FK_RELATIONSHIP_24 foreign key (GODINA, REDNI_BROJ_POPISA)
      references POPISNI_DOKUMENT (GODINA, REDNI_BROJ_POPISA) on delete restrict on update restrict;

alter table POPISNI_DOKUMENT add constraint FK_RELATIONSHIP_20 foreign key (GODINA)
      references POSLOVNA_GODINA (GODINA) on delete restrict on update restrict;

alter table POSLOVNI_PARTNER add constraint FK_RELATIONSHIP_2 foreign key (PIB)
      references PREDUZECE (PIB) on delete restrict on update restrict;

alter table PREDUZECE add constraint FK_RELATIONSHIP_4 foreign key (OZNAKA_DRZAVE, OZNAKA_NASELJENOG_MESTA)
      references NASELJENO_MESTO (OZNAKA_DRZAVE, OZNAKA_NASELJENOG_MESTA) on delete restrict on update restrict;

alter table PROMETNI_DOKUMENT add constraint FK_RELATIONSHIP_13 foreign key (PIB, OZNAKA_MAGACINA)
      references MAGACIN (PIB, OZNAKA_MAGACINA) on delete restrict on update restrict;

alter table PROMETNI_DOKUMENT add constraint FK_RELATIONSHIP_14 foreign key (MAG_PIB, MAG_OZNAKA_MAGACINA)
      references MAGACIN (PIB, OZNAKA_MAGACINA) on delete restrict on update restrict;

alter table PROMETNI_DOKUMENT add constraint FK_RELATIONSHIP_15 foreign key (POS_PIB, PIB_PARTNERA)
      references POSLOVNI_PARTNER (PIB, PIB_PARTNERA) on delete restrict on update restrict;

alter table PROMETNI_DOKUMENT add constraint FK_RELATIONSHIP_16 foreign key (GODINA)
      references POSLOVNA_GODINA (GODINA) on delete restrict on update restrict;

alter table STAVKA_POPISA add constraint FK_RELATIONSHIP_21 foreign key (GODINA, REDNI_BROJ_POPISA)
      references POPISNI_DOKUMENT (GODINA, REDNI_BROJ_POPISA) on delete restrict on update restrict;

alter table STAVKA_POPISA add constraint FK_RELATIONSHIP_22 foreign key (SIFRA_ARTIKLA)
      references ARTIKAL (SIFRA_ARTIKLA) on delete restrict on update restrict;

alter table STAVKA_PROMETNOG_DOKUMENTA add constraint FK_RELATIONSHIP_11 foreign key (SIFRA_ARTIKLA)
      references ARTIKAL (SIFRA_ARTIKLA) on delete restrict on update restrict;

alter table STAVKA_PROMETNOG_DOKUMENTA add constraint FK_RELATIONSHIP_12 foreign key (GODINA, BROJ)
      references PROMETNI_DOKUMENT (GODINA, BROJ) on delete restrict on update restrict;

alter table ZAPOSLENI add constraint FK_RELATIONSHIP_17 foreign key (PIB)
      references PREDUZECE (PIB) on delete restrict on update restrict;

