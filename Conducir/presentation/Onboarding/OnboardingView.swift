//
//  OnboardingView.swift
//  Conducir
//
//  Created by Manuel Duarte on 4/4/25.
//

import SwiftUI

struct OnboardingView: View {
    @Environment(\.dismiss) var dismiss
    @State private var animateCards: Bool = false
    
    // Lista de características que se mostrarán como cards
    private let features: [Feature] = [
        Feature(title: "Tests", description: "Practica con tests aleatorios para prepararte de forma dinámica.", systemName: "doc.text"),
        Feature(title: "Exámenes", description: "Simula exámenes completos y mide tu progreso real.", systemName: "pencil"),
        Feature(title: "Aprende", description: "Consulta normas de tránsito y señales oficiales.", systemName: "book"),
        Feature(title: "IA", description: "Resuelve tus dudas al instante con ayuda de la inteligencia artificial.", systemName: "cpu")
    ]
    
    var body: some View {
        ZStack {
            LinearGradient(
                gradient: Gradient(colors: [Color.white, .primaryColorApp.opacity(0.1)]),
                startPoint: .top,
                endPoint: .bottom
            )
            .ignoresSafeArea()
            
            VStack(spacing: 16) {
                Text("Bienvenido a Conducir")
                    .font(.system(size: 28, weight: .bold, design: .rounded))
                    .foregroundColor(.primaryColorApp)
                    .padding(.top, 40)
                
                Text("Prepárate para tu examen teórico de conducción en Nicaragua. Aprende sobre normas de tránsito, realiza tests, exámenes y utiliza la inteligencia artificial para resolver tus dudas.")
                    .font(.system(size: 16, weight: .regular, design: .rounded))
                    .multilineTextAlignment(.center)
                    .foregroundColor(.gray)
                    .padding(.horizontal, 30)
                    .padding(.bottom, 10)
                
                    VStack(spacing: 12) {
                        ForEach(features) { feature in
                            FeatureCardView(feature: feature, animateCards: animateCards)
                                .padding(.horizontal, 16)
                                .transition(.move(edge: .bottom).combined(with: .opacity))
                        }
                    }
                    .padding(.top, 10)
                
                Spacer()
                
                Button(action: {
                    dismiss()
                }) {
                    Text("Comenzar")
                        .bold()
                        .frame(maxWidth: .infinity)
                        .padding()
                        .background(Color.primaryColorApp)
                        .foregroundColor(.white)
                        .cornerRadius(12)
                        .padding(.horizontal, 30)
                }
                .padding(.bottom, 20)
            }
            .onAppear {
                withAnimation(.easeInOut(duration: 0.8)) {
                    animateCards = true
                }
            }
        }
    }
}

// MARK: - Vista previa
struct OnboardingView_Previews: PreviewProvider {
    static var previews: some View {
        OnboardingView()
    }
}
