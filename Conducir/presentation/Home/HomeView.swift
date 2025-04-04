//
//  HomeView.swift
//  Conducir
//
//  Created by Manuel Duarte on 4/4/25.
//

import SwiftUI

struct HomeView: View {
    @State private var showOnboarding: Bool = false
    
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
                        imageName: "ferry.fill"),
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
    }
}

#Preview {
    HomeView()
}
