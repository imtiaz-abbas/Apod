import SwiftUI
import shared

class ApodViewModel: ObservableObject {
  @Published var picture: AstronomyPicture?
  private var networkService: NetworkService

  init() {
    networkService = NetworkService()
  }
  
  func getAPOD() {
    DispatchQueue.main.async {
      self.networkService.fetchTodayAPOD { picture in
        self.picture = picture
      }
    }
  }
}
struct ContentView: View {
  @ObservedObject
  var viewModel = ApodViewModel()

	var body: some View {
    VStack {
      Text(viewModel.picture?.explanation ?? "")
    }
    .onAppear {
      DispatchQueue.main.async {
        viewModel.getAPOD()
      }
    }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
