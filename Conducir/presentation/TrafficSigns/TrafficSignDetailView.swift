//
//  TrafficSignDetailView.swift
//  Conducir
//
//  Created by Manuel Duarte on 13/7/25.
//

import SwiftUI
import Foundation

struct TrafficSignDetailView: View {
    let sign: TrafficSign
    @Environment(\.dismiss) private var dismiss
    
    var body: some View {
        ScrollView {
            VStack(spacing: 24) {
                // Imagen de la señal
                // Imagen de la señal con fondo
                let backgroundColor = Color.gray.opacity(0.1)
                let imageColor = getCategoryColor(for: sign.category)
                
                ZStack {
                    RoundedRectangle(cornerRadius: 16)
                        .fill(backgroundColor)
                        .frame(height: 250)
                    
                    Image(systemName: sign.category.systemImageName)
                        .resizable()
                        .scaledToFit()
                        .frame(width: 120, height: 120)
                        .foregroundColor(imageColor)
                }
                .padding(.horizontal)
                
                // Información de la señal
                VStack(alignment: .leading, spacing: 16) {
                    Text(sign.name)
                        .font(.title)
                        .fontWeight(.bold)
                    
                    // Categoría con color correspondiente
                    let categoryColor = getCategoryColor(for: sign.category)
                    
                    HStack {
                        Text("Categoría:")
                            .font(.headline)
                            .foregroundColor(.secondary)
                        
                        Text(sign.category.rawValue)
                            .font(.headline)
                            .foregroundColor(categoryColor)
                    }
                    
                    Text("Descripción")
                        .font(.headline)
                        .foregroundColor(.secondary)
                    
                    Text(sign.description)
                        .font(.body)
                        .foregroundColor(.primary)
                        .fixedSize(horizontal: false, vertical: true)
                    
                    // Información adicional sobre la categoría
                    let categoryTitle = "Sobre las señales " + sign.category.rawValue.lowercased()
                    let categoryDesc = sign.category.description
                    let backgroundFill = Color.gray.opacity(0.1)
                    
                    VStack(alignment: .leading, spacing: 12) {
                        Text(categoryTitle)
                            .font(.headline)
                            .foregroundColor(.secondary)
                        
                        Text(categoryDesc)
                            .font(.body)
                            .foregroundColor(.primary)
                            .fixedSize(horizontal: false, vertical: true)
                    }
                    .padding()
                    .background(
                        RoundedRectangle(cornerRadius: 12)
                            .fill(backgroundFill)
                    )
                    
                    // Normativa nicaragüense
                    let normativeText = getNormativeText(for: sign.category)
                    
                    VStack(alignment: .leading, spacing: 12) {
                        Text("Normativa nicaragüense")
                            .font(.headline)
                            .foregroundColor(.secondary)
                        
                        Text(normativeText)
                            .font(.body)
                            .foregroundColor(.primary)
                            .fixedSize(horizontal: false, vertical: true)
                    }
                    .padding()
                    .background(
                        RoundedRectangle(cornerRadius: 12)
                            .fill(backgroundFill)
                    )
                }
                .padding()
            }
        }
        .navigationBarTitleDisplayMode(.inline)
        .toolbar {
            ToolbarItem(placement: .navigationBarTrailing) {
                Button(action: {
                    dismiss()
                }) {
                    Image(systemName: "xmark.circle.fill")
                        .foregroundColor(.gray)
                }
            }
        }
    }
    
    // Función para obtener el color según la categoría
    private func getCategoryColor(for category: TrafficSignCategory) -> Color {
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
    
    private func getNormativeText(for category: TrafficSignCategory) -> String {
        switch category {
        case .regulatory:
            return "Según la Ley 431 de Nicaragua (Ley para el Régimen de Circulación Vehicular e Infracciones de Tránsito), las señales regulatorias son de obligatorio cumplimiento para todos los usuarios de la vía. Ignorar estas señales constituye una infracción que puede ser sancionada con multas según el artículo 26 de dicha ley."
        case .preventive:
            return "La normativa nicaragüense establece que las señales preventivas deben colocarse con anticipación al peligro que anuncian, generalmente entre 90 y 180 metros en zonas urbanas y hasta 500 metros en carreteras. Estas señales son de fondo amarillo con símbolos o leyendas en negro, según el Manual de Dispositivos de Control de Tránsito de Nicaragua."
        case .informative:
            return "El Manual Centroamericano de Dispositivos Uniformes para el Control del Tránsito, adoptado por Nicaragua, establece que las señales informativas deben ser de fondo verde o azul con texto o símbolos blancos. Su objetivo es guiar al conductor a lo largo de su itinerario y proporcionarle información útil para su viaje."
        case .temporary:
            return "Según la normativa nicaragüense, las señales temporales son de color naranja con símbolos negros y tienen prioridad sobre las señales permanentes. Las empresas constructoras o entidades que realizan trabajos en la vía están obligadas a colocarlas según el artículo 42 de la Ley 431."
        case .horizontal:
            return "La señalización horizontal en Nicaragua está regulada por el Manual de Dispositivos de Control de Tránsito y debe cumplir con estándares de reflectividad y durabilidad. Las líneas continuas, discontinuas y otros símbolos en el pavimento son de obligatorio cumplimiento según el artículo 25 de la Ley 431."
        }
    }
}

#Preview {
    TrafficSignDetailView(sign: TrafficSign(
        name: "Alto",
        description: "Indica que el conductor debe detenerse completamente antes de la línea de parada o cruce peatonal.",
        imageName: "sign_alto",
        category: .regulatory
    ))
}
