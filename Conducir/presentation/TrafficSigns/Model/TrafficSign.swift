//
//  TrafficSign.swift
//  Conducir
//
//  Created by Manuel Duarte on 13/7/25.
//

import Foundation

struct TrafficSign: Identifiable {
    let id = UUID()
    let name: String
    let description: String
    let imageName: String
    let category: TrafficSignCategory
}

enum TrafficSignCategory: String, CaseIterable, Identifiable {
    case regulatory = "Regulatorias"
    case preventive = "Preventivas"
    case informative = "Informativas"
    case temporary = "Temporales"
    case horizontal = "Horizontales"
    
    var id: String { self.rawValue }
    
    var description: String {
        switch self {
        case .regulatory:
            return "Indican limitaciones, prohibiciones o restricciones en el uso de la vía."
        case .preventive:
            return "Advierten sobre condiciones de la vía o riesgos existentes."
        case .informative:
            return "Guían al usuario proporcionando información útil para su viaje."
        case .temporary:
            return "Señalan zonas de trabajo temporal en la vía pública."
        case .horizontal:
            return "Marcas sobre el pavimento que complementan la señalización."
        }
    }
    
    var systemImageName: String {
        switch self {
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
}
