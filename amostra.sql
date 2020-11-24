-- Script com dados de exemplos apenas para desenvolvimento.

-- tabela usuario
INSERT INTO `usuario` (`matricula`, `usuario`, `nome_completo`, `empresa`, `email`, `senha`, `ativo`, `ultimo_login`, `criada_em`) VALUES ('12345', 'luiz.filipy', 'Luiz Filipy', 'Agroquart', 'luiz@email.com', '$2a$10$YNjImZ9.t8bxJhq39FJxoOw8eLsY8ython32T7KpVA1s7sTu7MhFi', '1', NULL, now());
INSERT INTO `usuario` (`matricula`, `usuario`, `nome_completo`, `empresa`, `email`, `senha`, `ativo`, `ultimo_login`, `criada_em`) VALUES ('54321', 'jean.carlos', 'Jean Carlos', 'Agroquart', 'jean@email.com', '$2a$10$YNjImZ9.t8bxJhq39FJxoOw8eLsY8ython32T7KpVA1s7sTu7MhFi', '1', NULL, now());

-- tabela permissoes
INSERT INTO `permissao` (`id`, `nome`) VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `permissao` (`id`, `nome`) VALUES ('2', 'ROLE_USUARIO');
INSERT INTO `permissao` (`id`, `nome`) VALUES ('3', 'ROLE_CRIAR_USUARIO');
INSERT INTO `permissao` (`id`, `nome`) VALUES ('4', 'ROLE_EDITAR_USUARIO');
INSERT INTO `permissao` (`id`, `nome`) VALUES ('5', 'ROLE_EXCLUIR_USUARIO');
INSERT INTO `permissao` (`id`, `nome`) VALUES ('6', 'ROLE_HOSPEDARIA');
INSERT INTO `permissao` (`id`, `nome`) VALUES ('7', 'ROLE_CRIAR_HOSPEDARIA');
INSERT INTO `permissao` (`id`, `nome`) VALUES ('8', 'ROLE_EDITAR_HOSPEDARIA');
INSERT INTO `permissao` (`id`, `nome`) VALUES ('9', 'ROLE_EXCLUIR_HOSPEDARIA');
INSERT INTO `permissao` (`id`, `nome`) VALUES ('10', 'ROLE_RESERVA');
INSERT INTO `permissao` (`id`, `nome`) VALUES ('11', 'ROLE_CRIAR_RESERVA');
INSERT INTO `permissao` (`id`, `nome`) VALUES ('12', 'ROLE_EDITAR_RESERVA');
INSERT INTO `permissao` (`id`, `nome`) VALUES ('13', 'ROLE_EXCLUIR_RESERVA');
INSERT INTO `permissao` (`id`, `nome`) VALUES ('14', 'ROLE_RELATORIO');

-- tabela usuario_permissoes
INSERT INTO `usuarios_permissoes` (`usuario_matricula`, `permissao_id`) VALUES ('12345', '1');
INSERT INTO `usuarios_permissoes` (`usuario_matricula`, `permissao_id`) VALUES ('12345', '2');
INSERT INTO `usuarios_permissoes` (`usuario_matricula`, `permissao_id`) VALUES ('12345', '3');
INSERT INTO `usuarios_permissoes` (`usuario_matricula`, `permissao_id`) VALUES ('12345', '4');
INSERT INTO `usuarios_permissoes` (`usuario_matricula`, `permissao_id`) VALUES ('12345', '5');
INSERT INTO `usuarios_permissoes` (`usuario_matricula`, `permissao_id`) VALUES ('12345', '6');
INSERT INTO `usuarios_permissoes` (`usuario_matricula`, `permissao_id`) VALUES ('12345', '7');
INSERT INTO `usuarios_permissoes` (`usuario_matricula`, `permissao_id`) VALUES ('12345', '8');
INSERT INTO `usuarios_permissoes` (`usuario_matricula`, `permissao_id`) VALUES ('12345', '9');
INSERT INTO `usuarios_permissoes` (`usuario_matricula`, `permissao_id`) VALUES ('12345', '10');
INSERT INTO `usuarios_permissoes` (`usuario_matricula`, `permissao_id`) VALUES ('12345', '11');
INSERT INTO `usuarios_permissoes` (`usuario_matricula`, `permissao_id`) VALUES ('12345', '12');
INSERT INTO `usuarios_permissoes` (`usuario_matricula`, `permissao_id`) VALUES ('12345', '13');
INSERT INTO `usuarios_permissoes` (`usuario_matricula`, `permissao_id`) VALUES ('12345', '14');

INSERT INTO `usuarios_permissoes` (`usuario_matricula`, `permissao_id`) VALUES ('54321', '1');
INSERT INTO `usuarios_permissoes` (`usuario_matricula`, `permissao_id`) VALUES ('54321', '2');
INSERT INTO `usuarios_permissoes` (`usuario_matricula`, `permissao_id`) VALUES ('54321', '3');
INSERT INTO `usuarios_permissoes` (`usuario_matricula`, `permissao_id`) VALUES ('54321', '4');
INSERT INTO `usuarios_permissoes` (`usuario_matricula`, `permissao_id`) VALUES ('54321', '5');
INSERT INTO `usuarios_permissoes` (`usuario_matricula`, `permissao_id`) VALUES ('54321', '6');
INSERT INTO `usuarios_permissoes` (`usuario_matricula`, `permissao_id`) VALUES ('54321', '7');
INSERT INTO `usuarios_permissoes` (`usuario_matricula`, `permissao_id`) VALUES ('54321', '8');
INSERT INTO `usuarios_permissoes` (`usuario_matricula`, `permissao_id`) VALUES ('54321', '9');
INSERT INTO `usuarios_permissoes` (`usuario_matricula`, `permissao_id`) VALUES ('54321', '10');
INSERT INTO `usuarios_permissoes` (`usuario_matricula`, `permissao_id`) VALUES ('54321', '11');
INSERT INTO `usuarios_permissoes` (`usuario_matricula`, `permissao_id`) VALUES ('54321', '12');
INSERT INTO `usuarios_permissoes` (`usuario_matricula`, `permissao_id`) VALUES ('54321', '13');
INSERT INTO `usuarios_permissoes` (`usuario_matricula`, `permissao_id`) VALUES ('54321', '14');

-- tabela hospedaria
INSERT INTO `hospedaria` (`id`, `nome_hospedaria`) VALUES ('1', 'Hospedaria 1');
INSERT INTO `hospedaria` (`id`, `nome_hospedaria`) VALUES ('2', 'Hospedaria 2');
INSERT INTO `hospedaria` (`id`, `nome_hospedaria`) VALUES ('3', 'Hospedaria 3');

-- tabela casa (tipo: 0 temporario, 1 fixo)
INSERT INTO `casa` (`id`, `numero`, `tipo`, `terceirizado`, `hospedaria_id`) VALUES ('1', '1', '0', '0', 1);
INSERT INTO `casa` (`id`, `numero`, `tipo`, `terceirizado`, `hospedaria_id`) VALUES ('2', '2', '0', '1', 1);
INSERT INTO `casa` (`id`, `numero`, `tipo`, `terceirizado`, `hospedaria_id`) VALUES ('3', '3', '0', '0', 1);

INSERT INTO `casa` (`id`, `numero`, `tipo`, `terceirizado`, `hospedaria_id`) VALUES ('4', '1', '0', '0', 2);
INSERT INTO `casa` (`id`, `numero`, `tipo`, `terceirizado`, `hospedaria_id`) VALUES ('5', '2', '0', '1', 2);

INSERT INTO `casa` (`id`, `numero`, `tipo`, `terceirizado`, `hospedaria_id`) VALUES ('6', '1', '1', '0', 3);
INSERT INTO `casa` (`id`, `numero`, `tipo`, `terceirizado`, `hospedaria_id`) VALUES ('7', '2', '1', '0', 3);
INSERT INTO `casa` (`id`, `numero`, `tipo`, `terceirizado`, `hospedaria_id`) VALUES ('8', '3', '1', '0', 3);

-- tabela quarto
INSERT INTO `quarto` (`id`, `capacidade`, `casa_id`) VALUES ('1', '2', '1');
INSERT INTO `quarto` (`id`, `capacidade`, `casa_id`) VALUES ('2', '3', '1');

INSERT INTO `quarto` (`id`, `capacidade`, `casa_id`) VALUES ('3', '2', '2');
INSERT INTO `quarto` (`id`, `capacidade`, `casa_id`) VALUES ('4', '2', '2');

INSERT INTO `quarto` (`id`, `capacidade`, `casa_id`) VALUES ('5', '2', '3');
INSERT INTO `quarto` (`id`, `capacidade`, `casa_id`) VALUES ('6', '2', '3');

INSERT INTO `quarto` (`id`, `capacidade`, `casa_id`) VALUES ('7', '2', '4');
INSERT INTO `quarto` (`id`, `capacidade`, `casa_id`) VALUES ('8', '3', '4');

INSERT INTO `quarto` (`id`, `capacidade`, `casa_id`) VALUES ('9', '3', '5');
INSERT INTO `quarto` (`id`, `capacidade`, `casa_id`) VALUES ('10', '2', '5');

INSERT INTO `quarto` (`id`, `capacidade`, `casa_id`) VALUES ('11', '2', '6');
INSERT INTO `quarto` (`id`, `capacidade`, `casa_id`) VALUES ('12', '3', '6');

INSERT INTO `quarto` (`id`, `capacidade`, `casa_id`) VALUES ('13', '2', '7');
INSERT INTO `quarto` (`id`, `capacidade`, `casa_id`) VALUES ('14', '2', '7');

INSERT INTO `quarto` (`id`, `capacidade`, `casa_id`) VALUES ('15', '2', '8');
INSERT INTO `quarto` (`id`, `capacidade`, `casa_id`) VALUES ('16', '2', '8');

-- tabela reserva
INSERT INTO `reserva` (`id`, `quarto_id`, `gerente_responsavel`, `nome_completo`, `matricula`, `email`, `empresa`, `data_inicio`, `data_termino`, `motivo`, `autorizada`, `arquivar`, `privado`) 
VALUES ('1', '1', 'Jose Silva', 'Ana Paula', '67890', 'ana@email.com', 'INSS', '2020-01-01 10:00:00', '2020-01-10 10:00:00', 'Fazer vistoria nos maquinários', '0', '0', '1');

INSERT INTO `reserva` (`id`, `quarto_id`, `gerente_responsavel`, `nome_completo`, `matricula`, `email`, `empresa`, `data_inicio`, `data_termino`, `motivo`, `autorizada`, `arquivar`, `privado`) 
VALUES ('2', '5', 'Jose Silva', 'Ana Paula', '67890', 'ana@email.com', 'INSS', '2020-01-15 10:00:00', '2020-01-25 10:00:00', 'Fazer manutenção nos maquinarios', '0', '0', '0');

INSERT INTO `reserva` (`id`, `quarto_id`, `gerente_responsavel`, `nome_completo`, `matricula`, `email`, `empresa`, `data_inicio`, `data_termino`, `motivo`, `autorizada`, `arquivar`, `privado`) 
VALUES ('3', '3', 'Leticia Santos', 'Carlos Costa', '65643', 'carlos@email.com', 'Google', '2020-04-01 10:00:00', '2020-04-05 10:00:00', 'Fazer vistoria nos computadores', '0', '0', '0');
