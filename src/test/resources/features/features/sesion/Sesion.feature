Feature: Valido flujo ingresar solicitudes de Tpo - AFP

  Scenario: Validación de solicitudes AFP en SAGCOM
    Given Ingreso Sagcom2 con los siguientes datos del archivo csv
    When  Ingreso usuario y contraseña validos
    And   Cerrar sesion
    Then  Cierro el navegador