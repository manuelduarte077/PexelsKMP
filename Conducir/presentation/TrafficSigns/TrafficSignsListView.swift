//
//  TrafficSignsListView.swift
//  Conducir
//
//  Created by Manuel Duarte on 13/7/25.
//

import SwiftUI

struct TrafficSignsListView: View {
    @Environment(\.dismiss) private var dismiss
    @StateObject private var viewModel = TrafficSignsViewModel()
    @State private var searchText = ""
    
    var body: some View {
        NavigationView {
            VStack(spacing: 0) {
                // Barra de búsqueda
                SearchBar(text: $searchText, placeholder: "Buscar señal de tránsito")
                    .padding(.horizontal)
                    .padding(.top, 10)
                
                // Lista de señales agrupadas por categoría
                List {
                    ForEach(TrafficSignCategory.allCases, id: \.self) { category in
                        Section(header: 
                            HStack {
                                Circle()
                                    .fill(TrafficSignsAssetManager.getColorFor(category: category))
                                    .frame(width: 12, height: 12)
                                Text(category.description)
                                    .font(.headline)
                                    .foregroundColor(TrafficSignsAssetManager.getColorFor(category: category))
                            }
                        ) {
                            let signs = viewModel.getFilteredSigns(for: category, searchText: searchText)
                            
                            if signs.isEmpty {
                                Text("No hay señales que coincidan con tu búsqueda")
                                    .foregroundColor(.gray)
                                    .italic()
                                    .padding(.vertical, 8)
                            } else {
                                ForEach(signs) { sign in
                                    NavigationLink(destination: TrafficSignDetailView(sign: sign)) {
                                        HStack(spacing: 16) {
                                            TrafficSignsAssetManager.getImageFor(signName: sign.imageName, category: sign.category)
                                                .resizable()
                                                .scaledToFit()
                                                .frame(width: 40, height: 40)
                                                .foregroundColor(TrafficSignsAssetManager.getColorFor(category: sign.category))
                                                .padding(8)
                                                .background(TrafficSignsAssetManager.getColorFor(category: sign.category).opacity(0.1))
                                                .clipShape(Circle())
                                            
                                            VStack(alignment: .leading, spacing: 4) {
                                                Text(sign.name)
                                                    .font(.headline)
                                                
                                                Text(sign.description)
                                                    .font(.subheadline)
                                                    .foregroundColor(.secondary)
                                                    .lineLimit(2)
                                            }
                                        }
                                        .padding(.vertical, 4)
                                    }
                                }
                            }
                        }
                    }
                }
                .listStyle(InsetGroupedListStyle())
            }
            .navigationTitle("Señales de Tránsito")
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItem(placement: .navigationBarLeading) {
                    Button(action: {
                        dismiss()
                    }) {
                        Image(systemName: "arrow.left")
                            .foregroundColor(.primaryColorApp)
                    }
                }
            }
        }
        .onAppear {
            // Cargar todas las señales
            viewModel.loadAllSigns()
        }
    }
}

struct SearchBar: View {
    @Binding var text: String
    var placeholder: String
    
    var body: some View {
        HStack {
            Image(systemName: "magnifyingglass")
                .foregroundColor(.gray)
            
            TextField(placeholder, text: $text)
                .foregroundColor(.primary)
            
            if !text.isEmpty {
                Button(action: {
                    text = ""
                }) {
                    Image(systemName: "xmark.circle.fill")
                        .foregroundColor(.gray)
                }
            }
        }
        .padding(10)
        .background(Color(.systemGray6))
        .cornerRadius(10)
    }
}

#Preview {
    TrafficSignsListView()
}
