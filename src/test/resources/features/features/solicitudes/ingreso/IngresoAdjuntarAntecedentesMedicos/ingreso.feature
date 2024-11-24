Feature: Ingresar Expedientes

  Scenario: Ingresar Expediente Parte 6
    Given Ingreso Sagcom2 con los siguientes datos del archivo csv
    When  Ingreso usuario y contraseña validos
    And   Ingresar nuevo expediente
    And   Ingreso los datos de la Solicitud
    And   Ingreso los datos de Antecedentes Laborales
    And   Ingreso Otros datos solicitud
    And   Ingreso Adjuntar Antecedentes Médicos
    And   Cerrar sesion
    Then  Cierro el navegador