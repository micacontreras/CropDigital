# CropDigital

Aplicación que consta de una lista, creada con un RecyclerView, donde se puede observar mas información de cada item mediante un Dailog personalizado y el usuario puede crear nuevos registros.

Para ello se han utilizado principalmente las siguientes herramientas:

# RecyclerView
El widget RecyclerView es una versión más avanzada y flexible de ListView.

En el modelo de RecyclerView, varios componentes diferentes trabajan juntos para mostrar tus datos. El contenedor general de tu interfaz de usuario es un objeto RecyclerView que agregas a tu diseño. El objeto RecyclerView se completa por sí solo con vistas que brinda el administrador de diseño que proporciones.

Las vistas incluidas en la lista están representadas por objetos contenedores de vistas. Esos objetos son instancias de una clase que defines extendiendo el objeto RecyclerView.ViewHolder. Cada objeto contenedor de vistas es responsable de mostrar un elemento individual con una vista.A medida que el usuario se desplaza por la lista, el objeto RecyclerView toma las vistas fuera de pantalla y vuelve a vincularlas con los datos que se desplazan en la pantalla.

Un adaptador, que creas extendiendo RecyclerView.Adapter, administra los objetos contenedores de vistas. El adaptador crea contenedores de vistas, según sea necesario, y los vincula con sus datos. Para hacerlo, asigna el contenedor de vistas a una posición y llama al método onBindViewHolder() del adaptador. Este método usa la posición del contenedor de vistas para determinar cuál debería ser el contenido, en función de su posición en la lista.

# Navigation Components
La navegación se refiere a las interacciones que permiten a los usuarios navegar a través, dentro y fuera de las diferentes piezas de contenido de tu app. El componente Navigation de Android Jetpack te permite implementar la navegación, desde simples clics de botones hasta patrones más complejos, como las barras de apps y los paneles laterales de navegación.
El componente Navigation consta de tres partes clave que se describen a continuación:

  *Gráfico de navegación: Es un recurso XML que contiene toda la información relacionada con la navegación en una ubicación centralizada. Esto incluye todas las áreas de contenido individuales dentro de tu app, llamadas destinos, así como las posibles rutas que un usuario puede tomar a través de tu app.
  
  *NavHost: Es un contenedor vacío que muestra los destinos de tu gráfico de navegación. El componente Navigation contiene una implementación NavHost predeterminada, NavHostFragment, que muestra destinos de fragmentos.
  
  *NavController: Es un objeto que administra la navegación de la app dentro de un NavHost. NavController orquesta el intercambio de contenido de destino en el objeto NavHost a medida que los usuarios se mueven a través de tu app.

# Material Design
Material design es una guía completa para el diseño visual, interactivo y de movimiento en plataformas y dispositivos. Android ofrece las siguientes funciones para ayudarte a crear apps de material design:

  *Un tema de app de material design para diseñar todos tus widgets de IU
  
  *Widgets para vistas complejas, como listas y tarjetas
  
  *Nuevas API para sombras y animaciones personalizadas
  
# Moshi
Moshi es una moderna biblioteca JSON para Android y Java. Facilita el análisis de JSON en objetos Java y Java en JSON.
Moshi tiene soporte incorporado para leer y escribir los tipos de datos principales de Java:

  *Primitivas (int, float, char ...) y sus contrapartes en caja (Integer, Float, Character ...).
  
  *Arreglos, colecciones, listas, conjuntos y mapas
  
  *Instrumentos de cuerda
  
  *Enumeraciones
