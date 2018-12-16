CREATE schema IF NOT EXISTS `optic` default char set utf8;
USE `optic`;
drop table if exists Darbuotojas;
drop table if exists Preke;
drop table if exists Pardavimas;
drop table if exists Receptas;
drop table if exists Uzsakymas;
drop table if exists Uzsakovas;


create table Darbuotojas
(
   Darbuotojo_ID        int not null,
   Darbuotojo_vardas	varchar(30) not null,
   Darbuotojo_tipas		varchar(30) not null,
   Prisijungimo_vardas	varchar(30) not null,
   Slaptazodis			varchar(30) not null,
   
   primary key (Darbuotojo_ID)
);
/*==============================================================*/
/* Table: Pardavimas                                            */
/*==============================================================*/
create table Pardavimas
(
   Pardavimo_ID         int not null,
   Suma                 float(8,2) not null,
   Pardavimo_data       timestamp not null,
   Prekes				varchar(100),
   Uzsakymai			varchar(100),
   primary key (Pardavimo_ID)
);

/*==============================================================*/
/* Table: Preke                                                 */
/*==============================================================*/
create table Preke
(
   Prekes_ID            int not null auto_increment,
   Prekes_Tipas         varchar(20) not null,
   Prekes_Kaina         double not null,
   Prekes_Gamintojas    varchar(30) not null,
   Prekes_Pavadinimas   varchar(50) not null,
   Prekes_Kiekis        int not null default 0,
   Rezervuota           int not null default 0,
   primary key (Prekes_ID)
);

/*==============================================================*/
/* Table: Receptas                                              */
/*==============================================================*/
create table Receptas
(
   Recepto_ID           int not null,
   Uzsakovo_ID          int,
   Desines_akies_sfera  decimal not null,
   Kaires_akies_sfera   decimal not null,
   Desines_akies_cilindras decimal,
   Kaires_akies_cilindras decimal,
   Desines_akies_asis   int,
   Kaires_akies_asis    int,
   Desines_akies_prizme decimal,
   Kaires_akies_prizme  decimal,
   Atstumas_tarp_vyzdziu_centru int not null,
   Paskirtis            varchar(25) not null,
   primary key (Recepto_ID)
);

/*==============================================================*/
/* Table: Uzsakovas                                             */
/*==============================================================*/
create table Uzsakovas
(
   Uzsakovo_ID          int not null,
   Vardas               varchar(20) not null,
   Pavarde              varchar(20),
   Telefono_numeris   varchar(15),
   El_pastas            varchar(50),
   primary key (Uzsakovo_ID)
);

/*==============================================================*/
/* Table: Uzsakymas                                             */
/*==============================================================*/
create table Uzsakymas
(
   Uzsakymo_ID          int not null,
   Uzsakovo_ID          int,
   Pagaminimo_data      date,
   Uzsakymo_kaina       float(8,2) not null,
   Avansas              float(8,2) not null,
   Lesio_rusis          varchar(50),
   Uzsakymas_gaminamas  bool not null,
   Uzsakymas_atiduotas  bool not null,
   primary key (Uzsakymo_ID)
);





alter table Receptas add constraint FK_Relationship_5 foreign key (Uzsakovo_ID)
      references Uzsakovas (Uzsakovo_ID) on delete restrict on update restrict;

alter table Uzsakymas add constraint FK_Relationship_2 foreign key (Uzsakovo_ID)
      references Uzsakovas (Uzsakovo_ID) on delete restrict on update restrict;

