-- Alterar a tabela para adicionar a coluna com enum de categorias (caso já exista)
ALTER TABLE TBL_CATEGORIAS ADD TIPO VARCHAR2(50) NOT NULL;

-- Insere valores iniciais para as categorias
INSERT INTO TBL_CATEGORIAS (categoria_id, TIPO) VALUES (1, 'ROUPAS');
INSERT INTO TBL_CATEGORIAS (categoria_id, TIPO) VALUES (2, 'ELETRONICOS');
INSERT INTO TBL_CATEGORIAS (categoria_id, TIPO) VALUES (3, 'MOVEIS');
INSERT INTO TBL_CATEGORIAS (categoria_id, TIPO) VALUES (4, 'LIVROS');
INSERT INTO TBL_CATEGORIAS (categoria_id, TIPO) VALUES (5, 'OUTROS');