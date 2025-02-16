INSERT INTO public.author (id, name) VALUES (1, 'Joshua Bloch');
INSERT INTO public.author (id, name) VALUES (2, 'Robert C. Martin');
INSERT INTO public.author (id, name) VALUES (3, 'Arulat N. Kami');

INSERT INTO public.book (id, title, published) VALUES (1, 'Effective Java', '2001-06-19');
INSERT INTO public.book (id, title, published) VALUES (2, 'Clean Code', '2008-08-11');

INSERT INTO public.book_author (book_id, author_id) VALUES (1, 1);
INSERT INTO public.book_author (book_id, author_id) VALUES (2, 2);
INSERT INTO public.book_author (book_id, author_id) VALUES (2, 3);

SELECT setval('author_id_seq', (SELECT MAX(id) FROM author), true);
SELECT setval('book_id_seq', (SELECT MAX(id) FROM book), true);

