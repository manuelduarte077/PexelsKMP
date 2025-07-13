//
//  HomeView.swift
//  Conducir
//
//  Created by Manuel Duarte on 4/4/25.
//

import SwiftUI

struct HomeView: View {
    @State private var showOnboarding: Bool = false
    @State private var showTrafficSigns: Bool = false
    @State private var showTrafficSignsList: Bool = false
    @State private var showTrafficSignsFeature: Bool = false
    
    // Observers para notificaciones
    private let trafficSignsObserver = NotificationCenter.default.publisher(for: NSNotification.Name("ShowTrafficSigns"))
    private let trafficSignsListObserver = NotificationCenter.default.publisher(for: NSNotification.Name("ShowTrafficSignsList"))
    
    let licenseCategories: [LicenseCategory] = [
        LicenseCategory(name: "Motos",
                        description: "Licencia para motocicletas y scooters.",
                        imageName: "scooter"),
        LicenseCategory(name: "Carros",
                        description: "Licencia para automóviles y vehículos de pasajeros.",
                        imageName: "car.fill"),
        LicenseCategory(name: "Livianos",
                        description: "Licencia para vehículos livianos y de bajo tonelaje.",
                        imageName: "car.2.fill"),
        LicenseCategory(name: "Barcos",
                        description: "Licencia para navegación en aguas interiores y costeras.",
                        imageName: "ferry.fill")
    ]
    
    var body: some View {
        NavigationView {
            ScrollView {
                VStack(spacing: 12) {
                    ForEach(licenseCategories) { category in
                        LicenseCategoryCardView(category: category)
                    }
                }
                .padding(.vertical)
            }
            .navigationTitle("Tipos de Licencias")
            .toolbar {
                ToolbarItem(placement: .navigationBarTrailing) {
                    Menu {
                        Button(action: {
                            showTrafficSignsFeature = true
                        }) {
                            Label("Aprender señales de tránsito", systemImage: "signpost.right.fill")
                        }
                        Button(action: {
                            // Acción para hacer test
                        }) {
                            Label("Hacer test", systemImage: "list.number")
                        }
                        Button(action: {
                            // Acción para usar Gemini
                        }) {
                            Label("Usar Gemini", systemImage: "message.fill")
                        }
                    } label: {
                        Image(systemName: "ellipsis.circle")
                            .font(.title)
                            .foregroundColor(Color(red: 1.0, green: 152/255, blue: 0))
                    }
                }
            }
        }
        .onAppear {
            withAnimation(.easeInOut(duration: 0.5)) {
                showOnboarding = true
            }
        }
        .sheet(isPresented: $showOnboarding) {
            OnboardingView()
                .presentationDetents([.large])
        }
        .sheet(isPresented: $showTrafficSignsFeature) {
            TrafficSignsFeatureView()
        }
        .sheet(isPresented: $showTrafficSigns) {
            TrafficSignsView()
        }
        .sheet(isPresented: $showTrafficSignsList) {
            TrafficSignsListView()
        }
        .onReceive(trafficSignsObserver) { _ in
            // Cuando recibimos la notificación, mostramos la vista de señales de tránsito por categoría
            showTrafficSigns = true
        }
        .onReceive(trafficSignsListObserver) { _ in
            // Cuando recibimos la notificación, mostramos la vista de lista de señales de tránsito
            showTrafficSignsList = true
        }
    }
}

#Preview {
    HomeView()
}
