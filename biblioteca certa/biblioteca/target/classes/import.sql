INSERT INTO tb_usuario (usr_nome, usr_email, usr_senha, usr_data_nascimento) VALUES ('Maria Silva', 'maria@gmail.com', '000', '2001-02-23');
INSERT INTO tb_livro (liv_titulo, liv_autor, liv_ano_publicacao) VALUES ('Dom Casmurro', 'Machado de Assis', 1899);
INSERT INTO tb_emprestimo (emp_usuario_id, emp_livro_id, emp_data_emprestimo) VALUES (1, 1, CURRENT_DATE);
