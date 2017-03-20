# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table evento (
  id                            bigint not null,
  nombre                        varchar(255),
  fecha                         timestamp,
  constraint pk_evento primary key (id)
);
create sequence EventoSequence;

create table musico (
  id                            bigint not null,
  nombre                        varchar(255),
  biografia                     varchar(255),
  correo                        varchar(255),
  tipo                          varchar(255),
  url_imagen                    varchar(255),
  constraint pk_musico primary key (id)
);
create sequence MusicoSequence;

create table propuestamusical (
  id                            bigint not null,
  constraint pk_propuestamusical primary key (id)
);
create sequence PropuestaMusicalSequence;


# --- !Downs

drop table if exists evento cascade;
drop sequence if exists EventoSequence;

drop table if exists musico cascade;
drop sequence if exists MusicoSequence;

drop table if exists propuestamusical cascade;
drop sequence if exists PropuestaMusicalSequence;

