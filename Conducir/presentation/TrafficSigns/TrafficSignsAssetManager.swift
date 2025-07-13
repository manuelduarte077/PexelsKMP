//
//  TrafficSignsAssetManager.swift
//  Conducir
//
//  Created by Manuel Duarte on 13/7/25.
//

import SwiftUI
import Foundation

struct TrafficSignsAssetManager {
    
    // Diccionario para almacenar los nombres de las imágenes
    private static var imageNameDictionary: [String: String] = [:]
    
    // Función para cargar las imágenes desde el archivo JSON
    static func loadImageNames() {
        guard let url = Bundle.main.url(forResource: "traffic_signs_images", withExtension: "json"),
              let data = try? Data(contentsOf: url),
              let json = try? JSONSerialization.jsonObject(with: data) as? [String: [String: String]] else {
            print("Error al cargar el archivo JSON de imágenes")
            return
        }
        
        // Procesar cada categoría
        for (_, signDict) in json {
            for (signName, imageName) in signDict {
                imageNameDictionary[signName] = imageName
            }
        }
    }
    
    // Función para obtener una imagen para una señal de tránsito
    static func getImageFor(signName: String, category: TrafficSignCategory) -> Image {
        // Intentar cargar las imágenes si el diccionario está vacío
        if imageNameDictionary.isEmpty {
            loadImageNames()
        }
        
        // Buscar la imagen en el diccionario
        if let systemName = imageNameDictionary[signName] {
            return Image(systemName: systemName)
        }
        
        // Si no se encuentra, devolver una imagen predeterminada basada en la categoría
        let systemName = getSystemImageName(for: category)
        return Image(systemName: systemName)
    }
    
    // Función para obtener un SF Symbol basado en la categoría de la señal
    static func getSystemImageName(for category: TrafficSignCategory) -> String {
        switch category {
        case .regulatory:
            return "exclamationmark.octagon.fill"
        case .preventive:
            return "exclamationmark.triangle.fill"
        case .informative:
            return "info.circle.fill"
        case .temporary:
            return "hammer.fill"
        case .horizontal:
            return "arrow.down.forward.and.arrow.up.backward"
        }
    }
    
    // Función para obtener un color basado en la categoría de la señal
    static func getColorFor(category: TrafficSignCategory) -> Color {
        switch category {
        case .regulatory:
            return .red
        case .preventive:
            return .yellow
        case .informative:
            return .blue
        case .temporary:
            return .orange
        case .horizontal:
            return .green
        }
    }
}
