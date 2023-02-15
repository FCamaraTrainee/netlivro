-- Table: public.author
drop table book_categories;

drop table category;

drop table book;

drop table author;


-- DROP TABLE IF EXISTS public.author;

CREATE TABLE IF NOT EXISTS public.author
(
    id uuid NOT NULL,
    active boolean,
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT author_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.author
    OWNER to postgres;

-- Table: public.category

-- DROP TABLE IF EXISTS public.category;

CREATE TABLE IF NOT EXISTS public.category
(
    id uuid NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT category_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.category
    OWNER to postgres;

-- Table: public.book

-- DROP TABLE IF EXISTS public.book;

CREATE TABLE IF NOT EXISTS public.book
(
    id uuid NOT NULL,
    active boolean,
    name character varying(255) COLLATE pg_catalog."default",
    year integer,
    author_id uuid,
    CONSTRAINT book_pkey PRIMARY KEY (id),
    CONSTRAINT fkklnrv3weler2ftkweewlky958 FOREIGN KEY (author_id)
        REFERENCES public.author (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.book
    OWNER to postgres;

-- Table: public.book_categories

-- DROP TABLE IF EXISTS public.book_categories;

CREATE TABLE IF NOT EXISTS public.book_categories
(
    books_id uuid NOT NULL,
    categories_id uuid NOT NULL,
    CONSTRAINT fkcua8hutsmi5x4wx20sdlptj9w FOREIGN KEY (categories_id)
        REFERENCES public.category (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkml7mxg34m6jmc1ict6usygnmp FOREIGN KEY (books_id)
        REFERENCES public.book (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.book_categories
    OWNER to postgres;

INSERT INTO author (id, name, active)
VALUES
    (gen_random_uuid(), 'Gabriel García Márquez', true),
    (gen_random_uuid(), 'J.K. Rowling', true),
    (gen_random_uuid(), 'Haruki Murakami', true),
    (gen_random_uuid(), 'Clarice Lispector', true),
    (gen_random_uuid(), 'Neil Gaiman', true),
    (gen_random_uuid(), 'Stephen King', true),
    (gen_random_uuid(), 'Toni Morrison', true),
    (gen_random_uuid(), 'Isabel Allende', true),
    (gen_random_uuid(), 'Jorge Luis Borges', true),
    (gen_random_uuid(), 'Virginia Woolf', true);

INSERT INTO book (id, active, name, year, author_id)
VALUES
    (gen_random_uuid(), true, 'Livro 1', 1246, (SELECT id FROM author WHERE name = 'Gabriel García Márquez')),
    (gen_random_uuid(), true, 'Livro 2', 3543, (SELECT id FROM author WHERE name = 'Gabriel García Márquez')),
    (gen_random_uuid(), true, 'Livro 3', 1234, (SELECT id FROM author WHERE name = 'Gabriel García Márquez')),
    (gen_random_uuid(), true, 'Livro 4', 5436, (SELECT id FROM author WHERE name = 'Gabriel García Márquez')),
    (gen_random_uuid(), true, 'Livro 5', 2384, (SELECT id FROM author WHERE name = 'Gabriel García Márquez'));


INSERT INTO book (id, active, name, year, author_id)
VALUES
    (gen_random_uuid(), true, 'Harry Potter and the Philosopher''s Stone', 1997, (SELECT id FROM author WHERE name = 'J.K. Rowling')),
    (gen_random_uuid(), true, 'Harry Potter and the Chamber of Secrets', 1998, (SELECT id FROM author WHERE name = 'J.K. Rowling')),
    (gen_random_uuid(), true, 'Harry Potter and the Prisoner of Azkaban', 1999, (SELECT id FROM author WHERE name = 'J.K. Rowling')),
    (gen_random_uuid(), true, 'Harry Potter and the Goblet of Fire', 2000, (SELECT id FROM author WHERE name = 'J.K. Rowling')),
    (gen_random_uuid(), true, 'Harry Potter and the Order of Phoenix', 2003, (SELECT id FROM author WHERE name = 'J.K. Rowling')),
    (gen_random_uuid(), true, 'Harry Potter and the Half-Blood Prince', 2005, (SELECT id FROM author WHERE name = 'J.K. Rowling')),
    (gen_random_uuid(), true, 'Harry Potter and the Deathly Hallows', 2007, (SELECT id FROM author WHERE name = 'J.K. Rowling'));

INSERT INTO book (id, active, name, year, author_id)
VALUES
    (gen_random_uuid(), true, 'A Hora da Estrela', 1977, (SELECT id FROM author WHERE name = 'Clarice Lispector')),
    (gen_random_uuid(), true, 'A Paixão Segundo G.H.', 1964, (SELECT id FROM author WHERE name = 'Clarice Lispector'));


WITH neil_gaiman_author AS (
    SELECT id FROM author WHERE name = 'Neil Gaiman'
)

INSERT INTO book (id, active, name, year, author_id)
SELECT gen_random_uuid(), true, 'Sandman', 1989, ng.id FROM neil_gaiman_author ng
UNION ALL
SELECT gen_random_uuid(), true, 'Stardust', 1999, ng.id FROM neil_gaiman_author ng
UNION ALL
SELECT gen_random_uuid(), true, 'Good Omens', 1990, ng.id FROM neil_gaiman_author ng
UNION ALL
SELECT gen_random_uuid(), true, 'American Gods', 2001, ng.id FROM neil_gaiman_author ng
UNION ALL
SELECT gen_random_uuid(), true, 'Caroline', 2002, ng.id FROM neil_gaiman_author ng;

-- obtém o id do autor Stephen King
WITH author_id AS (
    SELECT id FROM author WHERE name = 'Stephen King'
)

-- insere 3 livros na tabela book
INSERT INTO book (id, active, name, year, author_id)
SELECT
    gen_random_uuid() as id,
    true as active,
    'O Iluminado' as name,
    1977 as year,
    author_id.id as author_id
FROM author_id
UNION ALL
SELECT
    gen_random_uuid() as id,
    true as active,
    'It: A Coisa' as name,
    1986 as year,
    author_id.id as author_id
FROM author_id
UNION ALL
SELECT
    gen_random_uuid() as id,
    true as active,
    'Carrie, A Estranha' as name,
    1974 as year,
    author_id.id as author_id
FROM author_id;

INSERT INTO category (id, name)
VALUES
    (gen_random_uuid(), 'Romance'),
    (gen_random_uuid(), 'Suspense'),
    (gen_random_uuid(), 'Ficção Científica'),
    (gen_random_uuid(), 'Terror'),
    (gen_random_uuid(), 'Fantasia');

-- obtém o ID do autor J.K. Rowling e o ID da categoria "Fantasia"
WITH
    author_id AS (SELECT id FROM author WHERE name = 'J.K. Rowling'),
    category_id AS (SELECT id FROM category WHERE name = 'Fantasia')

-- insere os livros de J.K. Rowling na categoria "Fantasia"
INSERT INTO book_categories (books_id, categories_id)
SELECT
    book.id AS book_id,
    category_id.id AS category_id
FROM
    book
        JOIN author_id ON book.author_id = author_id.id
        JOIN category_id ON TRUE
WHERE
        author_id.id = book.author_id;

-- obtém o ID do autor J.K. Rowling e o ID da categoria "Fantasia"
WITH
    author_id AS (SELECT id FROM author WHERE name = 'Stephen King'),
    category_id AS (SELECT id FROM category WHERE name = 'Terror')

-- insere os livros de J.K. Rowling na categoria "Fantasia"
INSERT INTO book_categories (books_id, categories_id)
SELECT
    book.id AS book_id,
    category_id.id AS category_id
FROM
    book
        JOIN author_id ON book.author_id = author_id.id
        JOIN category_id ON TRUE
WHERE
        author_id.id = book.author_id;

-- obtém o ID do autor J.K. Rowling e o ID da categoria "Fantasia"
WITH
    author_id AS (SELECT id FROM author WHERE name = 'Stephen King'),
    category_id AS (SELECT id FROM category WHERE name = 'Suspense')

-- insere os livros de J.K. Rowling na categoria "Fantasia"
INSERT INTO book_categories (books_id, categories_id)
SELECT
    book.id AS book_id,
    category_id.id AS category_id
FROM
    book
        JOIN author_id ON book.author_id = author_id.id
        JOIN category_id ON TRUE
WHERE
        author_id.id = book.author_id;

-- obtém o ID do autor J.K. Rowling e o ID da categoria "Fantasia"
WITH
    author_id AS (SELECT id FROM author WHERE name = 'Neil Gaiman'),
    category_id AS (SELECT id FROM category WHERE name = 'Ficção Científica')

-- insere os livros de J.K. Rowling na categoria "Fantasia"
INSERT INTO book_categories (books_id, categories_id)
SELECT
    book.id AS book_id,
    category_id.id AS category_id
FROM
    book
        JOIN author_id ON book.author_id = author_id.id
        JOIN category_id ON TRUE
WHERE
        author_id.id = book.author_id;

-- obtém o ID do autor J.K. Rowling e o ID da categoria "Fantasia"
WITH
    author_id AS (SELECT id FROM author WHERE name = 'Neil Gaiman'),
    category_id AS (SELECT id FROM category WHERE name = 'Fantasia')

-- insere os livros de J.K. Rowling na categoria "Fantasia"
INSERT INTO book_categories (books_id, categories_id)
SELECT
    book.id AS book_id,
    category_id.id AS category_id
FROM
    book
        JOIN author_id ON book.author_id = author_id.id
        JOIN category_id ON TRUE
WHERE
        author_id.id = book.author_id;

-- obtém o ID do autor J.K. Rowling e o ID da categoria "Fantasia"
WITH
    author_id AS (SELECT id FROM author WHERE name = 'Neil Gaiman'),
    category_id AS (SELECT id FROM category WHERE name = 'Fantasia')

-- insere os livros de J.K. Rowling na categoria "Fantasia"
INSERT INTO book_categories (books_id, categories_id)
SELECT
    book.id AS book_id,
    category_id.id AS category_id
FROM
    book
        JOIN author_id ON book.author_id = author_id.id
        JOIN category_id ON TRUE
WHERE
        author_id.id = book.author_id;
