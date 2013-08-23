import :timer, only: [sleep: 1]
defmodule Main do
  @num 1000000

  def thread(pid) do
    :timer.sleep(10000)
    pid <- {:result, 1}
  end

  def collector(x) when x == @num do
    :done
  end

  def collector(x) do
    receive do
      {:result, n} ->
        IO.puts inspect n + x
          collector((x + n))
    end
  end

  def spawner(x) when x == @num do
    :done
  end

  def spawner(x) do
    spawn(__MODULE__, :thread, [self])
    spawner(x + 1)
  end

  def main() do
    spawner(0)
    collector(0)
  end

end

Main.main()
#Need to run with elixir --erl "+P 1000000" to allow us enough processes
