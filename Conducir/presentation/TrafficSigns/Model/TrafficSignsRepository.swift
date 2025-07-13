//
//  TrafficSignsRepository.swift
//  Conducir
//
//  Created by Manuel Duarte on 13/7/25.
//

import Foundation

class TrafficSignsRepository {
    static let shared = TrafficSignsRepository()
    
    // Inicializador público para permitir crear instancias desde otras clases
    init() {}
    
    // Señales regulatorias basadas en la normativa nicaragüense
    func getRegulatoryTrafficSigns() -> [TrafficSign] {
        return [
            TrafficSign(
                name: "Alto",
                description: "Indica que el conductor debe detenerse completamente antes de la línea de parada o cruce peatonal.",
                imageName: "sign_alto",
                category: .regulatory
            ),
            TrafficSign(
                name: "Ceda el Paso",
                description: "Indica que el conductor debe ceder el paso a los vehículos que circulan por la vía a la que se aproxima.",
                imageName: "sign_ceda",
                category: .regulatory
            ),
            TrafficSign(
                name: "No Estacionar",
                description: "Prohíbe el estacionamiento de vehículos en el área señalada.",
                imageName: "sign_no_estacionar",
                category: .regulatory
            ),
            TrafficSign(
                name: "Límite de Velocidad",
                description: "Indica la velocidad máxima permitida en kilómetros por hora.",
                imageName: "sign_velocidad",
                category: .regulatory
            ),
            TrafficSign(
                name: "No Adelantar",
                description: "Prohíbe adelantar a otros vehículos en el tramo señalado.",
                imageName: "sign_no_adelantar",
                category: .regulatory
            ),
            TrafficSign(
                name: "Dirección Obligatoria",
                description: "Indica la dirección que obligatoriamente deben seguir los vehículos.",
                imageName: "sign_direccion",
                category: .regulatory
            ),
            TrafficSign(
                name: "No Girar en U",
                description: "Prohíbe al conductor realizar un giro en U en ese punto.",
                imageName: "sign_no_giro_u",
                category: .regulatory
            ),
            TrafficSign(
                name: "Uso Obligatorio de Cinturón",
                description: "Indica la obligación de usar el cinturón de seguridad.",
                imageName: "sign_cinturon",
                category: .regulatory
            )
        ]
    }
    
    // Señales preventivas basadas en la normativa nicaragüense
    func getPreventiveTrafficSigns() -> [TrafficSign] {
        return [
            TrafficSign(
                name: "Curva Peligrosa",
                description: "Advierte sobre la proximidad de una curva peligrosa que requiere reducir la velocidad.",
                imageName: "sign_curva",
                category: .preventive
            ),
            TrafficSign(
                name: "Cruce de Peatones",
                description: "Advierte sobre la proximidad de un cruce peatonal.",
                imageName: "sign_peatones",
                category: .preventive
            ),
            TrafficSign(
                name: "Zona Escolar",
                description: "Advierte sobre la proximidad de una zona escolar donde se debe reducir la velocidad.",
                imageName: "sign_escuela",
                category: .preventive
            ),
            TrafficSign(
                name: "Intersección",
                description: "Advierte sobre la proximidad de una intersección donde debe cederse el paso.",
                imageName: "sign_interseccion",
                category: .preventive
            ),
            TrafficSign(
                name: "Superficie Resbaladiza",
                description: "Advierte sobre un tramo de vía que puede estar resbaladizo en ciertas condiciones.",
                imageName: "sign_resbaladizo",
                category: .preventive
            ),
            TrafficSign(
                name: "Ciclistas en la Vía",
                description: "Advierte sobre la presencia frecuente de ciclistas en la vía.",
                imageName: "sign_ciclistas",
                category: .preventive
            ),
            TrafficSign(
                name: "Ganado en la Vía",
                description: "Advierte sobre posible presencia de ganado cruzando la vía.",
                imageName: "sign_ganado",
                category: .preventive
            ),
            TrafficSign(
                name: "Descenso Peligroso",
                description: "Advierte sobre un tramo de vía con pendiente pronunciada.",
                imageName: "sign_descenso",
                category: .preventive
            )
        ]
    }
    
    // Señales informativas basadas en la normativa nicaragüense
    func getInformativeTrafficSigns() -> [TrafficSign] {
        return [
            TrafficSign(
                name: "Hospital",
                description: "Indica la proximidad o ubicación de un centro hospitalario.",
                imageName: "sign_hospital",
                category: .informative
            ),
            TrafficSign(
                name: "Estacionamiento",
                description: "Indica un área destinada para estacionamiento de vehículos.",
                imageName: "sign_estacionamiento",
                category: .informative
            ),
            TrafficSign(
                name: "Teléfono",
                description: "Indica la ubicación de un teléfono público.",
                imageName: "sign_telefono",
                category: .informative
            ),
            TrafficSign(
                name: "Gasolinera",
                description: "Indica la proximidad o ubicación de una estación de combustible.",
                imageName: "sign_gasolinera",
                category: .informative
            ),
            TrafficSign(
                name: "Restaurante",
                description: "Indica la proximidad o ubicación de un restaurante.",
                imageName: "sign_restaurante",
                category: .informative
            ),
            TrafficSign(
                name: "Aeropuerto",
                description: "Indica la proximidad o dirección hacia un aeropuerto.",
                imageName: "sign_aeropuerto",
                category: .informative
            ),
            TrafficSign(
                name: "Destino",
                description: "Indica la dirección y distancia hacia un destino específico.",
                imageName: "sign_destino",
                category: .informative
            ),
            TrafficSign(
                name: "Área de Descanso",
                description: "Indica la proximidad de un área designada para descanso.",
                imageName: "sign_descanso",
                category: .informative
            )
        ]
    }
    
    // Señales temporales basadas en la normativa nicaragüense
    func getTemporaryTrafficSigns() -> [TrafficSign] {
        return [
            TrafficSign(
                name: "Obras en la Vía",
                description: "Indica la presencia de trabajos de construcción o mantenimiento en la vía.",
                imageName: "sign_obras",
                category: .temporary
            ),
            TrafficSign(
                name: "Desvío",
                description: "Indica un desvío temporal debido a obras o incidentes en la vía principal.",
                imageName: "sign_desvio",
                category: .temporary
            ),
            TrafficSign(
                name: "Carril Cerrado",
                description: "Indica que un carril está cerrado temporalmente al tráfico.",
                imageName: "sign_carril_cerrado",
                category: .temporary
            ),
            TrafficSign(
                name: "Hombres Trabajando",
                description: "Advierte sobre la presencia de trabajadores en la vía.",
                imageName: "sign_trabajadores",
                category: .temporary
            ),
            TrafficSign(
                name: "Banderero",
                description: "Indica la presencia de una persona dirigiendo el tráfico con banderas o señales.",
                imageName: "sign_banderero",
                category: .temporary
            ),
            TrafficSign(
                name: "Maquinaria en la Vía",
                description: "Advierte sobre la presencia de maquinaria trabajando en la vía.",
                imageName: "sign_maquinaria",
                category: .temporary
            )
        ]
    }
    
    // Señales horizontales basadas en la normativa nicaragüense
    func getHorizontalTrafficSigns() -> [TrafficSign] {
        return [
            TrafficSign(
                name: "Línea Continua",
                description: "Indica que no se debe cruzar o rebasar en ese tramo de la vía.",
                imageName: "sign_linea_continua",
                category: .horizontal
            ),
            TrafficSign(
                name: "Línea Discontinua",
                description: "Indica que se permite el adelantamiento si las condiciones son seguras.",
                imageName: "sign_linea_discontinua",
                category: .horizontal
            ),
            TrafficSign(
                name: "Paso Peatonal",
                description: "Indica un área designada para el cruce de peatones.",
                imageName: "sign_paso_peatonal",
                category: .horizontal
            ),
            TrafficSign(
                name: "Flecha Direccional",
                description: "Indica la dirección que deben seguir los vehículos en un carril específico.",
                imageName: "sign_flecha",
                category: .horizontal
            ),
            TrafficSign(
                name: "Alto en Pavimento",
                description: "Palabra 'ALTO' marcada en el pavimento que indica detención obligatoria.",
                imageName: "sign_alto_pavimento",
                category: .horizontal
            ),
            TrafficSign(
                name: "Ceda en Pavimento",
                description: "Símbolo en el pavimento que indica ceder el paso.",
                imageName: "sign_ceda_pavimento",
                category: .horizontal
            )
        ]
    }
    
    // Obtener todas las señales de tránsito
    func getAllTrafficSigns() -> [TrafficSign] {
        return getRegulatoryTrafficSigns() + 
               getPreventiveTrafficSigns() + 
               getInformativeTrafficSigns() + 
               getTemporaryTrafficSigns() + 
               getHorizontalTrafficSigns()
    }
    
    // Obtener señales por categoría
    func getTrafficSigns(for category: TrafficSignCategory) -> [TrafficSign] {
        switch category {
        case .regulatory:
            return getRegulatoryTrafficSigns()
        case .preventive:
            return getPreventiveTrafficSigns()
        case .informative:
            return getInformativeTrafficSigns()
        case .temporary:
            return getTemporaryTrafficSigns()
        case .horizontal:
            return getHorizontalTrafficSigns()
        }
    }
}
