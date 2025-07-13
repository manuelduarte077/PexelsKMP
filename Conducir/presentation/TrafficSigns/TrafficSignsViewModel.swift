//
//  TrafficSignsViewModel.swift
//  Conducir
//
//  Created by Manuel Duarte on 13/7/25.
//

import Foundation
import SwiftUI

class TrafficSignsViewModel: ObservableObject {
    private let repository = TrafficSignsRepository()
    
    @Published var selectedCategory: TrafficSignCategory = .regulatory
    @Published var selectedSign: TrafficSign? = nil
    @Published var allSigns: [TrafficSign] = []
    @Published var searchText: String = ""
    @Published var showingDetail: Bool = false
    
    // Lista de categorías disponibles
    var categories: [TrafficSignCategory] {
        return TrafficSignCategory.allCases
    }
    
    // Computed property para obtener las señales filtradas
    var filteredSigns: [TrafficSign] {
        let signs = getSignsForSelectedCategory()
        
        if searchText.isEmpty {
            return signs
        }
        
        return signs.filter { sign in
            sign.name.localizedCaseInsensitiveContains(searchText) ||
            sign.description.localizedCaseInsensitiveContains(searchText)
        }
    }
    
    // Cargar todas las señales de todas las categorías
    func loadAllSigns() {
        var signs: [TrafficSign] = []
        
        for category in TrafficSignCategory.allCases {
            signs.append(contentsOf: repository.getTrafficSigns(for: category))
        }
        
        allSigns = signs
    }
    
    // Obtener todas las señales para la categoría seleccionada
    func getSignsForSelectedCategory() -> [TrafficSign] {
        return repository.getTrafficSigns(for: selectedCategory)
    }
    
    // Seleccionar una señal
    func selectSign(_ sign: TrafficSign) {
        selectedSign = sign
        showingDetail = true
    }
}
