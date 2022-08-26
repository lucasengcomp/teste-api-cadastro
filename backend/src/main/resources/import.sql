
INSERT INTO tb_cliente(nome_completo, cpf, email, telefone, data_nascimento, endereco) VALUES ('Lucas Galvao', '63401883038', 'lucasgalvao@email.com', '62999887765', '1995-01-01', 'Rua Paulista numero 01');
INSERT INTO tb_cliente(nome_completo, cpf, email, telefone, data_nascimento, endereco) VALUES ('Ana Veiga', '16868607067', 'anaveiga@email.com', '62999007767', '1996-12-31', 'Rua Mineiros valentes numero 01');
INSERT INTO tb_cliente(nome_completo, cpf, email, telefone, data_nascimento, endereco) VALUES ('Freddie Mercury', '36077498092', 'freddiemercuryoficial@email.com', '62999887718', '1946-09-05', 'Rua São João del Rey numero 44');
INSERT INTO tb_cliente(nome_completo, cpf, email, telefone, data_nascimento, endereco) VALUES ('Roger Taylor', '62089483040', 'rogertayloroficial@email.com', '62999887799', '1949-07-26', 'Avenida nova república qd01 lote 01');
INSERT INTO tb_cliente(nome_completo, cpf, email, telefone, data_nascimento, endereco) VALUES ('Jhon Deacon', '61624062024', 'jhondeaconoficial@email.com', '62999887755', '1951-08-19', 'Avenida Brasil numero 100');
INSERT INTO tb_cliente(nome_completo, cpf, email, telefone, data_nascimento, endereco) VALUES ('Brian May', '61624062024', 'brianmayoficial@email.com', '62999887711', '1947-07-19', 'Avenida Ibirapuera travessa com Paulista 01');

INSERT INTO tb_pedido(endereco, valor_total, cliente_id) VALUES ('Rua Paulista numero 01', 13250, 1);
INSERT INTO tb_pedido(endereco, valor_total, cliente_id) VALUES ('Rua Mineiros valentes numero 01', 897578, 3);
INSERT INTO tb_pedido(endereco, valor_total, cliente_id) VALUES ('Rua São João del Rey numero 44', 7897, 5);
INSERT INTO tb_pedido(endereco, valor_total, cliente_id) VALUES ('Avenida Brasil numero 100', 645346, 2);
INSERT INTO tb_pedido(endereco, valor_total, cliente_id) VALUES ('Avenida Ibirapuera travessa com Paulista 01', 5565678, 3);


INSERT INTO tb_item_pedido(sku, nome, valor_unitario, quantidade, valor_total, pedido_id) VALUES ('sku001', 'Mousepad', 12230, 4, 48920, 1);
INSERT INTO tb_item_pedido(sku, nome, valor_unitario, quantidade, valor_total, pedido_id) VALUES ('sku002', 'Peças automotivas', 14999, 4, 104993, 2);
INSERT INTO tb_item_pedido(sku, nome, valor_unitario, quantidade, valor_total, pedido_id) VALUES ('sku003', 'Itens de pesca', 59949, 8, 479592, 3);
INSERT INTO tb_item_pedido(sku, nome, valor_unitario, quantidade, valor_total, pedido_id) VALUES ('sku004', 'Itens de decoração', 12998, 12, 155976, 5);
INSERT INTO tb_item_pedido(sku, nome, valor_unitario, quantidade, valor_total, pedido_id) VALUES ('sku005', 'Conjunto de banheiro', 29969, 15, 449535, 4);
INSERT INTO tb_item_pedido(sku, nome, valor_unitario, quantidade, valor_total, pedido_id) VALUES ('sku006', 'Conjunto de sofá', 19999, 3, 59997, 3);
INSERT INTO tb_item_pedido(sku, nome, valor_unitario, quantidade, valor_total, pedido_id) VALUES ('sku007', 'Prataria', 49953, 9, 449577, 4);
INSERT INTO tb_item_pedido(sku, nome, valor_unitario, quantidade, valor_total, pedido_id) VALUES ('sku008', 'Colcha casal', 25949, 6, 155694, 1);
INSERT INTO tb_item_pedido(sku, nome, valor_unitario, quantidade, valor_total, pedido_id) VALUES ('sku009', 'Jogo de faca', 69989, 21, 1469769, 2);
