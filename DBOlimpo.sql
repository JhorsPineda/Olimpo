PGDMP     
    &                |            DBOlimpo    12.19    12.19 +    8           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            9           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            :           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            ;           1262    21127    DBOlimpo    DATABASE     �   CREATE DATABASE "DBOlimpo" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Spain.1252' LC_CTYPE = 'Spanish_Spain.1252';
    DROP DATABASE "DBOlimpo";
                postgres    false            �            1259    22064 
   apprentice    TABLE       CREATE TABLE public.apprentice (
    fk_id_person bigint NOT NULL,
    fk_id_student_sheet bigint NOT NULL,
    id_apprentice bigint NOT NULL,
    state character varying(255) NOT NULL,
    CONSTRAINT apprentice_state_check CHECK (((state)::text = ANY ((ARRAY['En_Formación'::character varying, 'Condicionado'::character varying, 'En_Plan_De_Mejora'::character varying, 'Retiro_Voluntario'::character varying, 'Cancelado'::character varying, 'Traslado'::character varying, 'Comite'::character varying])::text[])))
);
    DROP TABLE public.apprentice;
       public         heap    postgres    false            �            1259    22062    apprentice_id_apprentice_seq    SEQUENCE     �   CREATE SEQUENCE public.apprentice_id_apprentice_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.apprentice_id_apprentice_seq;
       public          postgres    false    203            <           0    0    apprentice_id_apprentice_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public.apprentice_id_apprentice_seq OWNED BY public.apprentice.id_apprentice;
          public          postgres    false    202            �            1259    22073 
   instructor    TABLE     �   CREATE TABLE public.instructor (
    fk_id_person bigint NOT NULL,
    id_instructor bigint NOT NULL,
    state character varying(255) NOT NULL
);
    DROP TABLE public.instructor;
       public         heap    postgres    false            �            1259    22071    instructor_id_instructor_seq    SEQUENCE     �   CREATE SEQUENCE public.instructor_id_instructor_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.instructor_id_instructor_seq;
       public          postgres    false    205            =           0    0    instructor_id_instructor_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public.instructor_id_instructor_seq OWNED BY public.instructor.id_instructor;
          public          postgres    false    204            �            1259    22081    person    TABLE     �  CREATE TABLE public.person (
    fk_id_rol bigint NOT NULL,
    id_person bigint NOT NULL,
    cellular_number character varying(255) NOT NULL,
    document_number character varying(255) NOT NULL,
    document_type character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    training_center character varying(255) NOT NULL,
    CONSTRAINT person_document_type_check CHECK (((document_type)::text = ANY ((ARRAY['TI'::character varying, 'CC'::character varying, 'Pasaporte'::character varying, 'CE'::character varying])::text[])))
);
    DROP TABLE public.person;
       public         heap    postgres    false            �            1259    22079    person_id_person_seq    SEQUENCE     }   CREATE SEQUENCE public.person_id_person_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.person_id_person_seq;
       public          postgres    false    207            >           0    0    person_id_person_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.person_id_person_seq OWNED BY public.person.id_person;
          public          postgres    false    206            �            1259    22093    rol    TABLE     i   CREATE TABLE public.rol (
    id_rol bigint NOT NULL,
    descripcion character varying(255) NOT NULL
);
    DROP TABLE public.rol;
       public         heap    postgres    false            �            1259    22091    rol_id_rol_seq    SEQUENCE     w   CREATE SEQUENCE public.rol_id_rol_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.rol_id_rol_seq;
       public          postgres    false    209            ?           0    0    rol_id_rol_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.rol_id_rol_seq OWNED BY public.rol.id_rol;
          public          postgres    false    208            �            1259    22101    sheet    TABLE     �   CREATE TABLE public.sheet (
    id_student_sheet bigint NOT NULL,
    jornada character varying(255) NOT NULL,
    phase character varying(255) NOT NULL,
    program character varying(255) NOT NULL
);
    DROP TABLE public.sheet;
       public         heap    postgres    false            �            1259    22099    sheet_id_student_sheet_seq    SEQUENCE     �   CREATE SEQUENCE public.sheet_id_student_sheet_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.sheet_id_student_sheet_seq;
       public          postgres    false    211            @           0    0    sheet_id_student_sheet_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.sheet_id_student_sheet_seq OWNED BY public.sheet.id_student_sheet;
          public          postgres    false    210            �
           2604    22067    apprentice id_apprentice    DEFAULT     �   ALTER TABLE ONLY public.apprentice ALTER COLUMN id_apprentice SET DEFAULT nextval('public.apprentice_id_apprentice_seq'::regclass);
 G   ALTER TABLE public.apprentice ALTER COLUMN id_apprentice DROP DEFAULT;
       public          postgres    false    202    203    203            �
           2604    22076    instructor id_instructor    DEFAULT     �   ALTER TABLE ONLY public.instructor ALTER COLUMN id_instructor SET DEFAULT nextval('public.instructor_id_instructor_seq'::regclass);
 G   ALTER TABLE public.instructor ALTER COLUMN id_instructor DROP DEFAULT;
       public          postgres    false    205    204    205            �
           2604    22084    person id_person    DEFAULT     t   ALTER TABLE ONLY public.person ALTER COLUMN id_person SET DEFAULT nextval('public.person_id_person_seq'::regclass);
 ?   ALTER TABLE public.person ALTER COLUMN id_person DROP DEFAULT;
       public          postgres    false    207    206    207            �
           2604    22096 
   rol id_rol    DEFAULT     h   ALTER TABLE ONLY public.rol ALTER COLUMN id_rol SET DEFAULT nextval('public.rol_id_rol_seq'::regclass);
 9   ALTER TABLE public.rol ALTER COLUMN id_rol DROP DEFAULT;
       public          postgres    false    209    208    209            �
           2604    22104    sheet id_student_sheet    DEFAULT     �   ALTER TABLE ONLY public.sheet ALTER COLUMN id_student_sheet SET DEFAULT nextval('public.sheet_id_student_sheet_seq'::regclass);
 E   ALTER TABLE public.sheet ALTER COLUMN id_student_sheet DROP DEFAULT;
       public          postgres    false    210    211    211            -          0    22064 
   apprentice 
   TABLE DATA           ]   COPY public.apprentice (fk_id_person, fk_id_student_sheet, id_apprentice, state) FROM stdin;
    public          postgres    false    203   \4       /          0    22073 
   instructor 
   TABLE DATA           H   COPY public.instructor (fk_id_person, id_instructor, state) FROM stdin;
    public          postgres    false    205   y4       1          0    22081    person 
   TABLE DATA           �   COPY public.person (fk_id_rol, id_person, cellular_number, document_number, document_type, email, last_name, name, password, training_center) FROM stdin;
    public          postgres    false    207   �4       3          0    22093    rol 
   TABLE DATA           2   COPY public.rol (id_rol, descripcion) FROM stdin;
    public          postgres    false    209   �6       5          0    22101    sheet 
   TABLE DATA           J   COPY public.sheet (id_student_sheet, jornada, phase, program) FROM stdin;
    public          postgres    false    211   �6       A           0    0    apprentice_id_apprentice_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public.apprentice_id_apprentice_seq', 1, false);
          public          postgres    false    202            B           0    0    instructor_id_instructor_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public.instructor_id_instructor_seq', 1, false);
          public          postgres    false    204            C           0    0    person_id_person_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.person_id_person_seq', 1, false);
          public          postgres    false    206            D           0    0    rol_id_rol_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.rol_id_rol_seq', 1, false);
          public          postgres    false    208            E           0    0    sheet_id_student_sheet_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.sheet_id_student_sheet_seq', 1, false);
          public          postgres    false    210            �
           2606    22070    apprentice apprentice_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public.apprentice
    ADD CONSTRAINT apprentice_pkey PRIMARY KEY (id_apprentice);
 D   ALTER TABLE ONLY public.apprentice DROP CONSTRAINT apprentice_pkey;
       public            postgres    false    203            �
           2606    22078    instructor instructor_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public.instructor
    ADD CONSTRAINT instructor_pkey PRIMARY KEY (id_instructor);
 D   ALTER TABLE ONLY public.instructor DROP CONSTRAINT instructor_pkey;
       public            postgres    false    205            �
           2606    22090    person person_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id_person);
 <   ALTER TABLE ONLY public.person DROP CONSTRAINT person_pkey;
       public            postgres    false    207            �
           2606    22098    rol rol_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.rol
    ADD CONSTRAINT rol_pkey PRIMARY KEY (id_rol);
 6   ALTER TABLE ONLY public.rol DROP CONSTRAINT rol_pkey;
       public            postgres    false    209            �
           2606    22109    sheet sheet_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.sheet
    ADD CONSTRAINT sheet_pkey PRIMARY KEY (id_student_sheet);
 :   ALTER TABLE ONLY public.sheet DROP CONSTRAINT sheet_pkey;
       public            postgres    false    211            �
           2606    22110 &   apprentice fkabxx1glrv9lf78vfday61l1qf    FK CONSTRAINT     �   ALTER TABLE ONLY public.apprentice
    ADD CONSTRAINT fkabxx1glrv9lf78vfday61l1qf FOREIGN KEY (fk_id_person) REFERENCES public.person(id_person);
 P   ALTER TABLE ONLY public.apprentice DROP CONSTRAINT fkabxx1glrv9lf78vfday61l1qf;
       public          postgres    false    207    2725    203            �
           2606    22125 "   person fkeqrus0445h68l6du8n2ufwbp2    FK CONSTRAINT     �   ALTER TABLE ONLY public.person
    ADD CONSTRAINT fkeqrus0445h68l6du8n2ufwbp2 FOREIGN KEY (fk_id_rol) REFERENCES public.rol(id_rol);
 L   ALTER TABLE ONLY public.person DROP CONSTRAINT fkeqrus0445h68l6du8n2ufwbp2;
       public          postgres    false    209    2727    207            �
           2606    22115 &   apprentice fknvkw5x0g2cxwjtyfcwl7pnch1    FK CONSTRAINT     �   ALTER TABLE ONLY public.apprentice
    ADD CONSTRAINT fknvkw5x0g2cxwjtyfcwl7pnch1 FOREIGN KEY (fk_id_student_sheet) REFERENCES public.sheet(id_student_sheet);
 P   ALTER TABLE ONLY public.apprentice DROP CONSTRAINT fknvkw5x0g2cxwjtyfcwl7pnch1;
       public          postgres    false    211    2729    203            �
           2606    22120 &   instructor fkpwy5op0b05fgtsg54qwbwuux2    FK CONSTRAINT     �   ALTER TABLE ONLY public.instructor
    ADD CONSTRAINT fkpwy5op0b05fgtsg54qwbwuux2 FOREIGN KEY (fk_id_person) REFERENCES public.person(id_person);
 P   ALTER TABLE ONLY public.instructor DROP CONSTRAINT fkpwy5op0b05fgtsg54qwbwuux2;
       public          postgres    false    207    205    2725            -      x������ � �      /      x������ � �      1     x���KR�@ ��u�.\�4�s'�Ą������4�	$��\y/6���Ѫ�t���DHW]B@A��,�4%��4t�*�4��5/j�a`c�2p-�k8�F�Ŵ��j�s=�k�[�㬤iQA�������F9|�C]P`����*$W%�'PV^�4�%����С��U����5R�>'��U1���j6Z�7���c.M�61�o���+���E'"IVTM�u�o]Nx�2oH��<!dc̃�g�5�z�9d�����-�N2ogK>Õ+M�U����>o�yDo�T"���۲7�t��tMU���w2��żz}�H���G����}4%�H	������&]�]h��͛i�=��b�����<
�[ߠ�Z��)�h����K~qY�'��i�p��r=o�v�y,�qq'��f��;a'=�,ޏ�A�O�R���|����d�����,�b~`�!����oO��ߟ�}}�I��b���a�g�ka��X����l�i[�-4�~��)�_�(v      3   %   x�3�t,(J�Kɬ�2���+.)*M.�/����� ��      5      x������ � �     