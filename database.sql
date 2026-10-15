--
-- PostgreSQL database dump
--

\restrict AqqaW9jhGbpwmBheshFAAU2oHcr4byIHCi1Et2IgXuMLATBqaWd2KqCCX9qwM03

-- Dumped from database version 17.11
-- Dumped by pg_dump version 17.11

-- Started on 2026-10-15 16:50:30

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 218 (class 1259 OID 16485)
-- Name: agence; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.agence (
    id bigint NOT NULL,
    nom character varying(100) NOT NULL
);


ALTER TABLE public.agence OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16484)
-- Name: agence_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.agence_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.agence_id_seq OWNER TO postgres;

--
-- TOC entry 4814 (class 0 OID 0)
-- Dependencies: 217
-- Name: agence_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.agence_id_seq OWNED BY public.agence.id;


--
-- TOC entry 220 (class 1259 OID 16494)
-- Name: client; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.client (
    id bigint NOT NULL,
    cin character varying(20) NOT NULL,
    nom character varying(100) NOT NULL,
    prenom character varying(100) NOT NULL,
    date_naissance date,
    telephone character varying(20) NOT NULL,
    email character varying(150),
    adresse character varying(255),
    ville character varying(100),
    code_postal character varying(10),
    profession character varying(100),
    agence_id bigint,
    statut character varying(20) DEFAULT 'ACTIF'::character varying NOT NULL,
    created_at timestamp without time zone DEFAULT now() NOT NULL,
    updated_at timestamp without time zone DEFAULT now() NOT NULL,
    CONSTRAINT ck_client_statut CHECK (((statut)::text = ANY ((ARRAY['ACTIF'::character varying, 'INACTIF'::character varying])::text[])))
);


ALTER TABLE public.client OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16493)
-- Name: client_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.client_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.client_id_seq OWNER TO postgres;

--
-- TOC entry 4815 (class 0 OID 0)
-- Dependencies: 219
-- Name: client_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.client_id_seq OWNED BY public.client.id;


--
-- TOC entry 4646 (class 2604 OID 16488)
-- Name: agence id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agence ALTER COLUMN id SET DEFAULT nextval('public.agence_id_seq'::regclass);


--
-- TOC entry 4647 (class 2604 OID 16497)
-- Name: client id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client ALTER COLUMN id SET DEFAULT nextval('public.client_id_seq'::regclass);


--
-- TOC entry 4653 (class 2606 OID 16492)
-- Name: agence agence_nom_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agence
    ADD CONSTRAINT agence_nom_key UNIQUE (nom);


--
-- TOC entry 4655 (class 2606 OID 16490)
-- Name: agence agence_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agence
    ADD CONSTRAINT agence_pkey PRIMARY KEY (id);


--
-- TOC entry 4657 (class 2606 OID 16505)
-- Name: client client_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (id);


--
-- TOC entry 4662 (class 2606 OID 16507)
-- Name: client uk_client_cin; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT uk_client_cin UNIQUE (cin);


--
-- TOC entry 4658 (class 1259 OID 16515)
-- Name: idx_client_agence; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_client_agence ON public.client USING btree (agence_id);


--
-- TOC entry 4659 (class 1259 OID 16513)
-- Name: idx_client_nom; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_client_nom ON public.client USING btree (lower((nom)::text));


--
-- TOC entry 4660 (class 1259 OID 16514)
-- Name: idx_client_prenom; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_client_prenom ON public.client USING btree (lower((prenom)::text));


--
-- TOC entry 4663 (class 2606 OID 16508)
-- Name: client client_agence_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_agence_id_fkey FOREIGN KEY (agence_id) REFERENCES public.agence(id);


-- Completed on 2026-10-15 16:50:30

--
-- PostgreSQL database dump complete
--

\unrestrict AqqaW9jhGbpwmBheshFAAU2oHcr4byIHCi1Et2IgXuMLATBqaWd2KqCCX9qwM03

