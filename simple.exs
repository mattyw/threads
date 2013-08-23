defmodule Main do

  def longRun(pid) do
    :timer.sleep(10000)
    pid <- {:result, :done}
  end

  def main() do
    spawn(__MODULE__, :longRun, [self])
    receive do
      {:result, value} ->
        IO.puts value
    end
  end

end

Main.main()
