import SwiftUI
import shared

struct ContentView: View {

    @State private var shouldOpenAbout = false
    @State private var shouldOpenSource = false

    var body: some View {
            NavigationStack {
                ArticlesScreen(viewModel: .init())
                    .toolbar {
                        ToolbarItemGroup(placement: .topBarTrailing) {
                            Button {
                                shouldOpenSource = true
                            } label: {
                                Label("Source", systemImage: "list.bullet.rectangle")
                                    .labelStyle(.titleAndIcon)
                            }
                            .popover(isPresented: $shouldOpenSource) {
                                SourcesScreen(viewModel: .init())
                            }
                            
                            Button {
                                shouldOpenAbout = true
                            } label: {
                                Label("About Device", systemImage: "info.circle")
                                    .labelStyle(.titleAndIcon)
                            }
                            .popover(isPresented: $shouldOpenAbout) {
                                AboutScreen()
                            }
                        }
                    }
            }
        }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
