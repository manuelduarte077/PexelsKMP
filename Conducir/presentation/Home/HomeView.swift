//
//  HomeView.swift
//  Conducir
//
//  Created by Manuel Duarte on 4/4/25.
//

import SwiftUI

struct HomeView: View {
    @State private var showOnboarding: Bool = false

    var body: some View {
        ZStack {
                Text("Pantalla Principal")
                      .font(.largeTitle)
                      .padding()
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
