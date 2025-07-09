# HotelReservaApp 🏨📱

Aplicación móvil para reservar hoteles desarrollada en **Kotlin con Jetpack Compose**, que consume servicios REST de un backend en Spring Boot.

---

## 🧩 Funcionalidades

- 🔐 Autenticación con JWT (login)
- 📍 SplashScreen para sesión persistente
- 🏨 Búsqueda de hoteles disponibles
- 🛏️ Visualización de habitaciones por hotel
- 📅 Selección de fecha de entrada y salida
- 👥 Definición de personas en la reserva
- 📄 Resumen y confirmación de reserva
- ✅ Registro real en base de datos (API `/api/reservas`)
- 🔓 Logout (cerrar sesión)

---

## 📲 Tecnologías usadas

- **Kotlin + Jetpack Compose**
- **Navigation Compose**
- **Retrofit + OkHttp**
- **Material 3**
- **DataStore Preferences** (para JWT)
- **MVVM (simple) + ViewModel**

---

## 🚀 Cómo correr el proyecto

1. Clona el repositorio o descarga el `.zip` del proyecto.
2. Ábrelo en **Android Studio Hedgehog o superior**.
3. Conecta un emulador o dispositivo Android físico.
4. Ejecuta el proyecto (`Run > Run 'app'`).
5. Usa las credenciales de login según el backend configurado.

> El backend por defecto debe correr en: `http://10.0.2.2:8080/`

---

## 🔗 Repositorio backend (Spring Boot)

https://github.com/freyesaviles/hotelreserva

---

## 📄 Licencia

MIT
