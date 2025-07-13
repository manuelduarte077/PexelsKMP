//
//  TrafficSignsFeatureView.swift
//  Conducir
//
//  Created by Manuel Duarte on 13/7/25.
//

import SwiftUI

struct TrafficSignsFeatureView: View {
    @Environment(\.dismiss) private var dismiss
    
    var body: some View {
        NavigationView {
            VStack(spacing: 0) {
                // Encabezado
                VStack(spacing: 16) {
                    Image(systemName: "signpost.right.fill")
                        .font(.system(size: 60))
                        .foregroundColor(.primaryColorApp)
                    
                    Text("Señales de Tránsito")
                        .font(.title)
                        .fontWeight(.bold)
                    
                    Text("Aprende las señales de tránsito oficiales de Nicaragua para conducir de manera segura y responsable.")
                        .font(.body)
                        .multilineTextAlignment(.center)
                        .padding(.horizontal)
                }
                .padding(.vertical, 30)
                .frame(maxWidth: .infinity)
                .background(Color.white)
                
                // Características
                ScrollView {
                    VStack(spacing: 20) {
                        FeatureItemView(
                            title: "Señales Regulatorias",
                            description: "Indican limitaciones, prohibiciones o restricciones en el uso de la vía.",
                            iconName: "exclamationmark.octagon.fill",
                            color: .red
                        )
                        
                        FeatureItemView(
                            title: "Señales Preventivas",
                            description: "Advierten sobre condiciones de la vía o riesgos existentes.",
                            iconName: "exclamationmark.triangle.fill",
                            color: .yellow
                        )
                        
                        FeatureItemView(
                            title: "Señales Informativas",
                            description: "Guían al usuario proporcionando información útil para su viaje.",
                            iconName: "info.circle.fill",
                            color: .blue
                        )
                        
                        FeatureItemView(
                            title: "Señales Temporales",
                            description: "Señalan zonas de trabajo temporal en la vía pública.",
                            iconName: "hammer.fill",
                            color: .orange
                        )
                        
                        FeatureItemView(
                            title: "Señalización Horizontal",
                            description: "Marcas sobre el pavimento que complementan la señalización.",
                            iconName: "arrow.down.forward.and.arrow.up.backward",
                            color: .green
                        )
                    }
                    .padding()
                }
                
                // Botones para explorar
                VStack(spacing: 16) {
                    Button(action: {
                        // Navegar a la vista principal de señales de tránsito
                        dismiss()
                        // Notificar a HomeView para mostrar la vista de señales de tránsito
                        NotificationCenter.default.post(name: NSNotification.Name("ShowTrafficSigns"), object: nil)
                    }) {
                        HStack {
                            Image(systemName: "square.grid.2x2")
                                .font(.headline)
                            Text("Ver por categorías")
                                .font(.headline)
                        }
                        .foregroundColor(.white)
                        .frame(height: 50)
                        .frame(maxWidth: .infinity)
                        .background(Color.primaryColorApp)
                        .cornerRadius(10)
                        .padding(.horizontal)
                    }
                    
                    Button(action: {
                        // Navegar a la vista de lista de señales de tránsito
                        dismiss()
                        // Notificar a HomeView para mostrar la vista de lista
                        NotificationCenter.default.post(name: NSNotification.Name("ShowTrafficSignsList"), object: nil)
                    }) {
                        HStack {
                            Image(systemName: "list.bullet")
                                .font(.headline)
                            Text("Ver todas las señales")
                                .font(.headline)
                        }
                        .foregroundColor(.primaryColorApp)
                        .frame(height: 50)
                        .frame(maxWidth: .infinity)
                        .background(Color.primaryColorApp.opacity(0.1))
                        .cornerRadius(10)
                        .overlay(
                            RoundedRectangle(cornerRadius: 10)
                                .stroke(Color.primaryColorApp, lineWidth: 1)
                        )
                        .padding(.horizontal)
                    }
                }
                .padding(.vertical)
            }
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItem(placement: .navigationBarLeading) {
                    Button(action: {
                        dismiss()
                    }) {
                        Image(systemName: "xmark")
                            .foregroundColor(.primaryColorApp)
                    }
                }
            }
        }
    }
}

struct FeatureItemView: View {
    let title: String
    let description: String
    let iconName: String
    let color: Color
    
    var body: some View {
        HStack(spacing: 16) {
            Image(systemName: iconName)
                .font(.system(size: 30))
                .foregroundColor(color)
                .frame(width: 60, height: 60)
                .background(color.opacity(0.1))
                .clipShape(Circle())
            
            VStack(alignment: .leading, spacing: 4) {
                Text(title)
                    .font(.headline)
                    .fontWeight(.bold)
                
                Text(description)
                    .font(.subheadline)
                    .foregroundColor(.secondary)
                    .fixedSize(horizontal: false, vertical: true)
            }
            
            Spacer()
            
            Image(systemName: "chevron.right")
                .foregroundColor(.gray)
        }
        .padding()
        .background(Color.white)
        .cornerRadius(12)
        .shadow(color: Color.black.opacity(0.05), radius: 5, x: 0, y: 2)
        .padding(.horizontal)
    }
}

#Preview {
    TrafficSignsFeatureView()
}
