import SwiftUI
import sharedBevanSpike

struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel

    var body: some View {
        ListView(phrases: viewModel.greetings)
            .task {
                await self.viewModel.startObserving()
            }
    }
}


extension ContentView {
    
    @MainActor
    class ViewModel: ObservableObject {
        @Published var greetings: [RocketLaunch] = []
        var allUsersSubscription: KmmSubscription? = nil
        let apiCall = RocketComponent()

        func startObserving() async {
            do {
                cobaFlow()
                // sudah tidak pakai suspend function yang kembaliane async await
//                try await Task.sleep(nanoseconds: 3_000_000_000) // Add a 3-second delay
//                let response = try await apiCall.getRocket()
//                self.greetings = response
            } catch {}
           
        }

        func cobaFlow() {
            allUsersSubscription = apiCall.getRockeTFlow().subscribe(
            onEach: { data in
                print(data)
                if let dataArray = data as? [Any] {
                    self.greetings = dataArray.compactMap { $0 as? RocketLaunch }
                }

            }, onCompletion: { error in
                print("error")
            }
            )
        }
    }
}

struct ListView: View {
    let phrases: [RocketLaunch]

    var body: some View {
        List(phrases, id: \.self) { data in
            VStack {
                Text(data.missionOne)
                    .font(.title)
                Text("\(data.flightNumber)")
                    .font(.headline)
                    .foregroundColor(.red)
            }
            
        }
    }
}
