Feature: Valido flujo ingresar solicitudes de Tpo - AFP

  Scenario: Iniciar sesión con un usuario de Perfil: "Ejecutivo AFP", "Sin Comisión"
    Given Ingreso Sagcom2 con los siguientes datos del archivo csv
    When  Ingreso usuario y contraseña validos
    And   Cerrar sesion
    Then  Cierro el navegador