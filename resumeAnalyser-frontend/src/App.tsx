import ResumeUpload from "./resumeUpload";
import ErrorBoundary from "./ErrorBoundary";
import "./App.css";

function App() {
  // const [count, setCount] = useState(0)

  return (
    <ErrorBoundary>
      <div className="App">
        <ResumeUpload />
      </div>
    </ErrorBoundary>
  );
}

export default App;
