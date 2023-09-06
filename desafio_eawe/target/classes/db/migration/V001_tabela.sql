create table geraXml(
                        id Integer auto_increment not null primary key,
                        codigo INTEGER not null,
                        data DATETIME not null,
                        regiao_sigla varchar(50) not null,
                        geracao_valor double not null,
                        compra_valor double not null,
                        precoMedio double not null
);