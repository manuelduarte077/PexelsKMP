# Solución para Error de Parsing JSON en PexelsKMP

## Problema Identificado

El error original era:
```
Error: Illegal input: Unexpected JSON token at offset 1721: Failed to parse int for input '2150733147' at path: $.photos[1].photographer_id
```

El problema se debía a que el campo `photographer_id` en la respuesta de la API de Pexels contenía valores muy grandes (como `2150733147`) que excedían el rango de un `Int` en Kotlin.

## Solución Implementada

### 1. Simplificación del Modelo de Datos

**Archivo modificado:** `composeApp/src/commonMain/kotlin/dev/donmanuel/pexelskmp/app/domain/models/Photo.kt`

- **Antes:** El modelo incluía `photographerId: Int` que causaba errores de parsing
- **Después:** Removido completamente el campo `photographer_id` del modelo

```kotlin
@Serializable
data class Photo(
    val id: Int,
    val width: Int,
    val height: Int,
    val url: String,
    val photographer: String,
    @SerialName("photographer_url") val photographerUrl: String,
    @SerialName("avg_color") val avgColor: String,
    val src: PhotoSrc,
    val liked: Boolean,
    val alt: String
)
```

### 2. Mejora de la Configuración de JSON

**Archivo modificado:** `composeApp/src/commonMain/kotlin/dev/donmanuel/pexelskmp/app/di/networkModule.kt`

Se mejoró la configuración de JSON para ser más tolerante con errores:

```kotlin
json(Json {
    ignoreUnknownKeys = true      // Ignora campos no definidos en el modelo
    isLenient = true             // Permite JSON más flexible
    coerceInputValues = true     // Convierte tipos automáticamente
    useArrayPolymorphism = true  // Mejor manejo de arrays
    encodeDefaults = true        // Incluye valores por defecto
    explicitNulls = false        // No incluye nulls explícitos
    prettyPrint = false          // Optimización de rendimiento
    classDiscriminator = "type"  // Discriminador de clases
})
```

### 3. Mejora del Manejo de Errores

**Archivo modificado:** `composeApp/src/commonMain/kotlin/dev/donmanuel/pexelskmp/app/data/repositories/PhotoRepositoryImpl.kt`

Se agregó manejo específico para errores de serialización:

```kotlin
catch (e: SerializationException) {
    println("Error de serialización: ${e.message}")
    Result.failure(Exception("Error al procesar los datos de la API: ${e.message}"))
}
```

### 4. Debug Mejorado en el Servicio API

**Archivo modificado:** `composeApp/src/commonMain/kotlin/dev/donmanuel/pexelskmp/app/data/api/PexelsApiService.kt`

Se agregó logging para debug cuando ocurren errores de serialización:

```kotlin
catch (e: SerializationException) {
    println("Error de serialización en getCuratedPhotos: ${e.message}")
    // Obtiene el JSON raw para debug
    val rawJson = rawResponse.bodyAsText()
    println("JSON raw recibido: ${rawJson.take(500)}...")
}
```

## Beneficios de la Solución

1. **Eliminación del Error:** El campo problemático ya no causa errores de parsing
2. **Robustez:** La aplicación es más tolerante a cambios en la API
3. **Debug Mejorado:** Mejor información para diagnosticar problemas futuros
4. **Mantenibilidad:** Código más simple y fácil de mantener

## Consideraciones Adicionales

- Si en el futuro necesitas el `photographer_id`, puedes agregarlo como `Long?` (nullable Long)
- La configuración `ignoreUnknownKeys = true` permite que la API evolucione sin romper la aplicación
- El logging agregado ayuda a diagnosticar problemas similares en el futuro

## Pruebas Recomendadas

1. Ejecutar la aplicación y verificar que carga las fotos correctamente
2. Probar la funcionalidad de búsqueda
3. Verificar que no aparecen errores de parsing en los logs
4. Probar con diferentes páginas de resultados 