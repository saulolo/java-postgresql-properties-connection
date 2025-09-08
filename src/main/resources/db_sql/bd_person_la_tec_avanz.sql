-- ========================================
-- Script: 1. Crear la base de datos principal
-- Base de datos: bd_person_la_tec_avanz
-- ========================================
CREATE
DATABASE bd_person_la_tec_avanz;

-- ========================================
-- Script: 2. Crear tabla registros
-- Contiene los datos de login de usuarios
-- ========================================
-- Asegúrate de estar conectado a la BD bd_person_la_tec_avanz antes de ejecutar lo siguiente:
CREATE TABLE public.registros
(
    id         SERIAL PRIMARY KEY,
    usuario    VARCHAR(25) UNIQUE NOT NULL,
    contrasena VARCHAR(25) NOT NULL
);

-- añadir comentarios
COMMENT ON TABLE public.registros IS 'Tabla de registros de usuario';
COMMENT ON COLUMN public.registros.usuario IS 'Nombre de usuario (hasta 25 caracteres)';
COMMENT ON COLUMN public.registros.contrasena IS 'Contraseña almacenada (hasta 25 caracteres';

-- ========================================
-- Script: 3. Restricciones adicionales
-- Evitar que un mismo nombre de usuario se repita
-- ========================================
ALTER TABLE public.registros
    ADD CONSTRAINT usuario_unico UNIQUE (usuario);

