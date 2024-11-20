Feature: Validación de Expedientes

  Scenario: Expediente, Aceptar Adminisibilidad de la solicitud con Documentos
    Given Ingreso Sagcom2 con los siguientes datos del archivo csv
    When  Ingreso usuario y contraseña validos
    And   Seleccionar Mi Trabajo Administrativo
    And   Seleccionando Análisis de Admisibilidad
    And   Aceptar Adminisibilidad de la solicitud con Aprobación de Documentos
    And   Cerrar sesion
    Then  Cierro el navegador

  Scenario: Expediente, Aceptar Adminisibilidad de la solicitud rechazando documentos
    Given Ingreso Sagcom2 con los siguientes datos del archivo csv
    When  Ingreso usuario y contraseña validos
    And   Seleccionar Mi Trabajo Administrativo
    And   Seleccionando Análisis de Admisibilidad
    And   Aceptar Adminisibilidad de la solicitud con Rechazo de Documentos
    And   Cerrar sesion
    Then  Cierro el navegador

  Scenario: Expediente, Rechazar Adminisibilidad de la solicitud aprobando documentos
    Given Ingreso Sagcom2 con los siguientes datos del archivo csv
    When  Ingreso usuario y contraseña validos
    And   Seleccionar Mi Trabajo Administrativo
    And   Seleccionando Análisis de Admisibilidad
    And   Rechazar Adminisibilidad de la solicitud con Aprobación de Documentos
    And   Cerrar sesion
    Then  Cierro el navegador

  Scenario: Expediente, Rechazar Adminisibilidad de la solicitud rechazando documentos
    Given Ingreso Sagcom2 con los siguientes datos del archivo csv
    When  Ingreso usuario y contraseña validos
    And   Seleccionar Mi Trabajo Administrativo
    And   Seleccionando Análisis de Admisibilidad
    And   Rechazar Adminisibilidad de la solicitud con Rechazo de Documentos
    And   Cerrar sesion
    Then  Cierro el navegador