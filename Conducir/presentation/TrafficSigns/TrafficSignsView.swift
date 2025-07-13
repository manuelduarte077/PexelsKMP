//
//  TrafficSignsView.swift
//  Conducir
//
//  Created by Manuel Duarte on 13/7/25.
//

import SwiftUI

struct TrafficSignsView: View {
    @StateObject private var viewModel = TrafficSignsViewModel()
    @Environment(\.dismiss) private var dismiss
    
    var body: some View {
        NavigationView {
            VStack(spacing: 0) {

                CategoriesView(viewModel: viewModel)
                
                // Barra de búsqueda
                SearchBarView(searchText: $viewModel.searchText)
                
                // Lista de señales
                if viewModel.filteredSigns.isEmpty {
                    EmptyStateView()
                } else {
                    TrafficSignsGridView(signs: viewModel.filteredSigns) { sign in
                        viewModel.selectSign(sign)
                    }
                }
            }
            .navigationTitle("Señales de Tránsito")
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItem(placement: .navigationBarLeading) {
                    Button(action: {
                        dismiss()
                    }) {
                        Image(systemName: "chevron.left")
                            .foregroundColor(.primaryColorApp)
                    }
                }
            }
            .sheet(isPresented: $viewModel.showingDetail) {
                if let selectedSign = viewModel.selectedSign {
                    TrafficSignDetailView(sign: selectedSign)
                }
            }
        }
    }
}

// MARK: - Categories View
struct CategoriesView: View {
    @ObservedObject var viewModel: TrafficSignsViewModel
    
    var body: some View {
        ScrollView(.horizontal, showsIndicators: false) {
            HStack(spacing: 15) {
                ForEach(viewModel.categories) { category in
                    CategoryButton(
                        category: category,
                        isSelected: viewModel.selectedCategory == category
                    ) {
                        withAnimation {
                            viewModel.selectedCategory = category
                        }
                    }
                }
            }
            .padding(.horizontal)
            .padding(.vertical, 10)
        }
        .background(Color.white)
        .shadow(color: Color.black.opacity(0.05), radius: 5, x: 0, y: 5)
    }
}

// MARK: - Search Bar View
struct SearchBarView: View {
    @Binding var searchText: String
    
    var body: some View {
        HStack {
            Image(systemName: "magnifyingglass")
                .foregroundColor(.gray)
            
            TextField("Buscar señal de tránsito", text: $searchText)
                .autocapitalization(.none)
                .disableAutocorrection(true)
            
            if !searchText.isEmpty {
                Button(action: {
                    searchText = ""
                }) {
                    Image(systemName: "xmark.circle.fill")
                        .foregroundColor(.gray)
                }
            }
        }
        .padding()
        .background(Color(.systemGray6))
        .cornerRadius(10)
        .padding()
    }
}

// MARK: - Empty State View
struct EmptyStateView: View {
    var body: some View {
        VStack(spacing: 20) {
            Image(systemName: "exclamationmark.triangle")
                .font(.system(size: 50))
                .foregroundColor(.primaryColorApp)
            
            Text("No se encontraron señales")
                .font(.title3)
                .fontWeight(.medium)
            
            Text("Intenta con otra categoría o término de búsqueda")
                .font(.subheadline)
                .foregroundColor(.secondary)
                .multilineTextAlignment(.center)
        }
        .padding()
        .frame(maxWidth: .infinity, maxHeight: .infinity)
    }
}

// MARK: - Traffic Signs Grid View
struct TrafficSignsGridView: View {
    let signs: [TrafficSign]
    let onSignTapped: (TrafficSign) -> Void
    
    var body: some View {
        ScrollView {
            LazyVGrid(columns: [GridItem(.flexible()), GridItem(.flexible())], spacing: 16) {
                ForEach(signs) { sign in
                    TrafficSignCardView(sign: sign)
                        .onTapGesture {
                            onSignTapped(sign)
                        }
                }
            }
            .padding()
        }
    }
}

struct CategoryButton: View {
    let category: TrafficSignCategory
    let isSelected: Bool
    let action: () -> Void
    
    var body: some View {
        Button(action: action) {
            VStack(spacing: 8) {
                Image(systemName: category.systemImageName)
                    .font(.system(size: 24))
                    .foregroundColor(isSelected ? .white : .primaryColorApp)
                
                Text(category.rawValue)
                    .font(.caption)
                    .fontWeight(.medium)
                    .foregroundColor(isSelected ? .white : .primary)
            }
            .frame(width: 90, height: 80)
            .background(
                RoundedRectangle(cornerRadius: 12)
                    .fill(isSelected ? Color.primaryColorApp : Color.white)
                    .shadow(color: Color.black.opacity(0.1), radius: 4, x: 0, y: 2)
            )
        }
    }
}

struct TrafficSignCardView: View {
    let sign: TrafficSign
    
    var body: some View {
        VStack(alignment: .center, spacing: 12) {
            ZStack {
                RoundedRectangle(cornerRadius: 8)
                    .fill(Color.gray.opacity(0.1))
                    .frame(height: 120)
                
                TrafficSignsAssetManager.getImageFor(signName: sign.imageName, category: sign.category)
                    .resizable()
                    .scaledToFit()
                    .frame(width: 60, height: 60)
                    .foregroundColor(TrafficSignsAssetManager.getColorFor(category: sign.category))
            }
            
            Text(sign.name)
                .font(.headline)
                .fontWeight(.bold)
                .multilineTextAlignment(.center)
                .lineLimit(2)
            
            Text(sign.description)
                .font(.caption)
                .foregroundColor(.secondary)
                .multilineTextAlignment(.center)
                .lineLimit(3)
                .padding(.horizontal, 4)
        }
        .padding()
        .background(Color.white)
        .cornerRadius(12)
        .shadow(color: Color.black.opacity(0.1), radius: 4, x: 0, y: 2)
    }
}

#Preview {
    TrafficSignsView()
}
