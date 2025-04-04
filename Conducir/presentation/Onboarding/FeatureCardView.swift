//
//  FeatureCardView.swift
//  Conducir
//
//  Created by Manuel Duarte on 4/4/25.
//

import SwiftUI

struct FeatureCardView: View {
    let feature: Feature
    let animateCards: Bool
    
    var body: some View {
        HStack(alignment: .center, spacing: 16) {
            Image(systemName: feature.systemName)
                .font(.system(size: 28, weight: .semibold))
                .foregroundColor(.primaryColorApp)
                .scaleEffect(animateCards ? 1.0 : 0.5)
                .opacity(animateCards ? 1.0 : 0.0)
                .animation(.easeOut(duration: 0.6), value: animateCards)
            
            VStack(alignment: .leading, spacing: 4) {
                Text(feature.title)
                    .font(.system(size: 18, weight: .bold, design: .rounded))
                    .foregroundColor(.primaryColorApp)
                Text(feature.description)
                    .font(.system(size: 14, weight: .regular, design: .rounded))
                    .foregroundColor(.gray)
                    .fixedSize(horizontal: false, vertical: true)
            }
            Spacer()
        }
        .padding()
        .background(Color.white)
        .cornerRadius(12)
        .shadow(color: .black.opacity(0.1), radius: 4, x: 0, y: 2)
    }
}
