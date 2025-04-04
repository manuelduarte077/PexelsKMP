//
//  SplashViewModel.swift
//  Conducir
//
//  Created by Manuel Duarte on 4/4/25.
//

import Foundation

final class SplashViewModel: ObservableObject {
    @Published var isActive: Bool = false
        init() {
            DispatchQueue.main.asyncAfter(deadline: .now() + 2.0) { 
            self.isActive = true
        }
    }
}
