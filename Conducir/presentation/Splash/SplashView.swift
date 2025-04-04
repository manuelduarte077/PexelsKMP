//
//  SplashView.swift
//  Conducir
//
//  Created by Manuel Duarte on 4/4/25.
//

import SwiftUI

struct SplashView: View {
    @StateObject private var viewModel = SplashViewModel()
    
    var body: some View {
        Group {
            if viewModel.isActive {
                HomeView()
            } else {
                ZStack {
                    Color.white.edgesIgnoringSafeArea(.all)
                    VStack(spacing: 20) {
                        Image("logo")
                            .resizable()
                            .aspectRatio(contentMode: .fit)
                            .frame(width: 200, height: 200)
                        
                        Text("Bienvenido a ConducirTest")
                            .font(.headline)
                            .foregroundColor(.black)
                    }
                }
            }
        }
    }
}

#Preview {
    SplashView()
}
