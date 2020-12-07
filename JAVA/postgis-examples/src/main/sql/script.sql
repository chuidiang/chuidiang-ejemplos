--
-- PostgreSQL database dump
--

-- Dumped from database version 12.0
-- Dumped by pg_dump version 12.0

-- Started on 2020-12-07 13:42:47

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2 (class 3079 OID 30204)
-- Name: postgis; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS postgis WITH SCHEMA public;


--
-- TOC entry 3694 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION postgis; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION postgis IS 'PostGIS geometry, geography, and raster spatial types and functions';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 211 (class 1259 OID 31316)
-- Name: geometry_tracks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.geometry_tracks (
    id integer NOT NULL,
    fusion_id bigint,
    start_timestamp time without time zone NOT NULL,
    stop_timestamp time without time zone,
    trajectory public.geometry
);


ALTER TABLE public.geometry_tracks OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 31314)
-- Name: geometry_tracks_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.geometry_tracks_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.geometry_tracks_id_seq OWNER TO postgres;

--
-- TOC entry 3695 (class 0 OID 0)
-- Dependencies: 210
-- Name: geometry_tracks_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.geometry_tracks_id_seq OWNED BY public.geometry_tracks.id;


--
-- TOC entry 209 (class 1259 OID 31296)
-- Name: point_tracks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.point_tracks (
    id integer NOT NULL,
    fusion_id bigint NOT NULL,
    "timestamp" time without time zone NOT NULL,
    point public.geometry
);


ALTER TABLE public.point_tracks OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 31294)
-- Name: point_tracks_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.point_tracks_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.point_tracks_id_seq OWNER TO postgres;

--
-- TOC entry 3696 (class 0 OID 0)
-- Dependencies: 208
-- Name: point_tracks_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.point_tracks_id_seq OWNED BY public.point_tracks.id;


--
-- TOC entry 3548 (class 2604 OID 31319)
-- Name: geometry_tracks id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.geometry_tracks ALTER COLUMN id SET DEFAULT nextval('public.geometry_tracks_id_seq'::regclass);


--
-- TOC entry 3547 (class 2604 OID 31299)
-- Name: point_tracks id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.point_tracks ALTER COLUMN id SET DEFAULT nextval('public.point_tracks_id_seq'::regclass);


--
-- TOC entry 3555 (class 2606 OID 31324)
-- Name: geometry_tracks geography_tracks_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.geometry_tracks
    ADD CONSTRAINT geography_tracks_pkey PRIMARY KEY (id);


--
-- TOC entry 3552 (class 2606 OID 31304)
-- Name: point_tracks point_tracks_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.point_tracks
    ADD CONSTRAINT point_tracks_pkey PRIMARY KEY (id);


--
-- TOC entry 3556 (class 1259 OID 31419)
-- Name: geometry_tracks_fusion_id__idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX geometry_tracks_fusion_id__idx ON public.geometry_tracks USING btree (fusion_id);


--
-- TOC entry 3557 (class 1259 OID 31325)
-- Name: geometry_tracks_geom_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX geometry_tracks_geom_idx ON public.geometry_tracks USING gist (trajectory);


--
-- TOC entry 3553 (class 1259 OID 31305)
-- Name: point_tracks_timestamp_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX point_tracks_timestamp_idx ON public.point_tracks USING btree (fusion_id, "timestamp" DESC);


-- Completed on 2020-12-07 13:42:47

--
-- PostgreSQL database dump complete
--

