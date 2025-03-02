//
//  SourceListScreen.swift
//  iosApp
//
//  Created by Nilesh Rathod on 02/03/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared

extension SourcesScreen {
    
    @MainActor
        class SourceViewModelWrapper: ObservableObject {
            let articlesViewModel: ArticlesViewModel
            
            
            init() {
                articlesViewModel = ArticleInjector().articlesViewModel
                articlesState = articlesViewModel.articlesState.value
            }
            
            @Published var articlesState: ArticlesState
            
            func startObserving() {
                Task {
                    for await articlesS in articlesViewModel.articlesState {
                        self.articlesState = articlesS
                    }
                }
            }
        }
}

struct SourcesScreen: View {
    @Environment(\.dismiss)
    private var dismiss
    
    @ObservedObject private(set) var viewModel: SourcesScreen.SourceViewModelWrapper
    
    var body: some View {
        NavigationStack {
            VStack {
                
                if let error = viewModel.articlesState.error {
                    ErrorMessage(message: error)
                }
                
                if viewModel.articlesState.loading {
                    Loader()
                }
                
                if !viewModel.articlesState.sources.isEmpty {
                    ScrollView {
                        LazyVStack(spacing: 10) {
                            ForEach(viewModel.articlesState.sources, id: \.self) { source in
                                SourceItemView(name: source.name, desc: source.description_, origin: "\(source.country) -  \(source.language)")
                            }
                        }
                    }
                }
            }.onAppear{
                self.viewModel.startObserving()
            }
            .navigationTitle("Sources")
            .toolbar {
                ToolbarItem(placement: .primaryAction) {
                    Button {
                        dismiss()
                    } label: {
                        Text("Done")
                            .bold()
                    }
                }
            }
        }
    }
}

struct SourceItemView: View {
    let name: String
    let desc: String
    let origin: String
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text(name)
                .font(.title)
                .fontWeight(.bold)
            Text(desc)
            Text(origin).frame(maxWidth: .infinity, alignment: .trailing).foregroundStyle(.gray)
        }
        .padding(16)
    }
}
