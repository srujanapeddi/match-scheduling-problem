   import java.util.*;
   import java .util . stream.Collectors;
   public class matches{

   public static void main(String[] args) throws Exception {
        ArrayList<group> teams=new ArrayList<>();
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        System.out.println("enter the teams:");
        for(int i=0;i<n;i++)
        {
            teams.add(new group(sc.next()));
        }
        
        List<game> matches=Scheduler.createSchedule(teams);
        System.out.println(matches);
        Simulator.directMatches(matches);
        Simulator.showPointsTable(teams,matches);
    }
}
class game
{
    private group team1;
    private group team2;
    private group winner;
    private group loser;
    game(group team,group team3)
    {
        this.team1=team;
        this.team2=team3;
    }
    public group getTeam1()
    {
        return team1;
    }
    public group getTeam2()
    {
        return team2;
    }
    public group getWinner()
    {
        return winner;
    }
    public void setWinner(group winner)
    {
        this.winner=winner;
    }
    public group getLoser()
    {
        return loser;
    }
    public void setLoser(group loser)
    {
        this.loser=loser;
    }
    @Override
    public String toString()
    {
        String matchDescription = team1 +" vs "+team2;
        if(winner!=null)
        {
            String result="\n winner="+this.winner.toString()+"Loser="+this.loser.toString();
            matchDescription+=result;
        }
        return matchDescription;
    }
}
class Scheduler
{
    public static List<game> createSchedule(List<group> teams)
    {
        List<game> matches=new ArrayList<>();
        for(int i=0;i<teams.size();i++)
        {
            for(int j=i+1;j<teams.size();j++)
            {
                game match=new game(teams.get(i), teams.get(j));
                matches.add(match);
            }
        }
        return matches;
    }
}
class Simulator
{
    /**
     * @param matches
     */
    public static void directMatches(List<game> matches)
    {
        for(game match:matches)
        {
            int random=(int)((Math.random()*10)+1);
            if(random%2==0)
            {
                match.setWinner(match.getTeam1());
                match.setLoser(match.getTeam2());
            }
            else
            {
                match.setWinner(match.getTeam2());
                match.setLoser(match.getTeam1());
            }
        }
    }
    public static void showPointsTable(List<group> teams,List<game> matches)
    {
        for(group team:teams)
        {
            int wonGames=matches.stream().filter(m ->m.getWinner().equals(team)).collect(Collectors.toList()).size();
            int lostGames=matches.stream().filter(m ->m.getLoser().equals(team)).collect(Collectors.toList()).size();
            System.out.println(team);
            System.out.println(wonGames);
            System.out.println(lostGames);
        }
    }
}
class group
{
    private String name;
    group(String name)
    {
        this.name=name;
    }
    @Override
    public String toString()
    {
        return name;
    }
    public boolean equals(Object obj)
    {
        return this.name.equals(((group)obj).name);
    }
}